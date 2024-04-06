# File-Hider
Hide any Zip file inside an Image (For educational purposes only!)


## In the configuration of R2000 RFID reader module, I have advanced during last times. Now I have a new question. If I want to get the antenna setting info I send an Hex command and module returns the next info: A5 5A 00 0A 2b 22 11 12 0D 0A where data 22 11 are DByte1 DByte0 and means that antenna 1, 5, 10 and 14 are chosen. I don't know how to get this info from 22 11 Hex code.

DByte1 (Hex 22) means that antennas 10 and 14 are chosen DByte0 (Hex 11) means that antennas 1 and 5 are chosen



It's a simple binary format. 0x2211 is a 16-bit integer where each bit represents an antenna:

```
format('%016b', 0x2211)
#=> "0010001000010001"
#      ^   ^    ^   ^
#     14  10    5   1
```
So to figure out if an antenna is set, you just have to check if the corresponding bit is set to 1:

```
n = 0x2211
n[0] == 1 #=> true
n[1] == 1 #=> false
n[2] == 1 #=> false
n[3] == 1 #=> false
n[4] == 1 #=> true
```
Note that the indices are 0-based, i.e. n[0] is antenna 1.

How can I get 10,14 values from Hex 11 and 1,5 values from Hex 22 in Ruby?

You could extract the binary digits, combine each digit with its 1-based index (with_index), select those bits that are set to 1 and extract their indices via map:

```
0x2211.digits(2)                 #=> [1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1]
      .each.with_index(1)        #=> [[1, 1], [0, 2], [0, 3], [0, 4], [1, 5], [0, 6], [0, 7], [0, 8], [0, 9], [1, 10], [0, 11], [0, 12], [0, 13], [1, 14]]
      .select { |b, i| b == 1 }  #=> [[1, 1], [1, 5], [1, 10], [1, 14]]
      .map(&:last)               #=> [1, 5, 10, 14]
Or using bit operators & and <<:
```

antennas = []
0.upto(15) { |i| antennas.push(i + 1) if (0x2211 & (1 << i)) != 0 }
antennas #=> [1, 5, 10, 14]
