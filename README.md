# jtroika
Java implementation of Troika hash function


Dumb implementation of the troika hash function (version 1.0.1) in java.
This implementation is a quick and dirty port of the reference implementation. (no optimization, may contains bug)

Use it at your own risks.

Reference implementation and documentation can be found on [cyber-crypt website](https://www.cyber-crypt.com/troika/)


## Usage

Default hash using a message formatted in trytes or trits 

    //24 rounds, hash length: 81 trytes (or 243 trits)
    String hashInTrytes = JTroika.hash("A0MESSAGE");
    String hashInTrytesFromTrits = JTroika.hash(new int[]{0,1,1,2,2,2});

Number of rounds can be specified
    
    //3 rounds, hash length: 81 trytes (or 243 trits)
    String hashInTrytes = JTroika.hash("A0MESSAGE", 3);
    String hashInTrytesFromTrits = JTroika.hash(new int[]{0,1,1,2,2,2}, 3);

Length of the hash can be changed

    //3 rounds, hash length: 27 trytes (or 81 trits)
    String hashInTrytes = JTroika.hash("A0MESSAGE", 3, 81);
    String hashInTrytesFromTrits = JTroika.hash(new int[]{0,1,1,2,2,2}, 3, 81);

Similarly, you can get the hash as trits (java int type: 0, 1 or 2)

    //24 rounds, hash length: 81 trytes (or 243 trits)
    int[] hashInTrits = JTroika.hashAsTrits("A0MESSAGE");
    int[] hashInTritsFromTrits = JTroika.hashAsTrits(new int[]{0,1,1,2,2,2});
        
    //3 rounds, hash length: 81 trytes (or 243 trits)
    int[] hashInTrits = JTroika.hashAsTrits("A0MESSAGE", 3);
    int[] hashInTritsFromTrits = JTroika.hashAsTrits(new int[]{0,1,1,2,2,2}, 3);
        
    //3 rounds, hash length: 27 trytes (or 81 trits)
    int[] hashInTrits = JTroika.hashAsTrits("A0MESSAGE", 3, 81);
    int[] hashInTritsFromTrits = JTroika.hashAsTrits(new int[]{0,1,1,2,2,2}, 3, 81);

## Note

A trit is represented by a java int of value 0, 1 or 2

A tryte is represented by 0 or a capital letter (the character '9' commonly used in IOTA is here replaced by the character '0')