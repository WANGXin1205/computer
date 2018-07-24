package com.growlithe.computer.common.base.utils;

import org.apache.spark.sql.sources.In;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author : Growlithe
 * @Date : 2018/7/24 20:54
 * @Description
 */
public class HashMapUtils {

    /*
      只是用来学习 hashtable, hashMap，concurrentHashMap 不涉及其他实质性内容,然后K,V都用Integer, jdk使用1.8
      HashTable
      底层数组+链表实现，无论key还是value都不能为null，线程安全，实现线程安全的方式是在修改数据时锁住整个HashTable，
      效率低，ConcurrentHashMap做了相关优化
      初始size为11，扩容：newsize = oldSize*2+1
      计算index的方法：index = (hash & 0x7FFFFFFF) % tab.length

      HashMap jdk1.8
      底层 数组+链表+红黑树 实现，在 Java8 中，当链表中的元素超过了 8 个以后，会将链表转换为红黑树，
      在这些位置进行查找的时候可以降低时间复杂度为 O(logN)。
      可以存储null键和null值，线程不安全
      初始size为16，扩容：newsize = oldSize*2，size一定为2的n次幂
      扩容针对整个Map，每次扩容时，原来数组中的元素依次重新计算存放位置，并重新插入
      插入元素后才判断该不该扩容，有可能无效扩容（插入后如果扩容，如果没有再次插入，就会产生无效扩容）
      当Map中元素总数超过Entry数组的75%，触发扩容操作，为了减少链表长度，元素分配更均匀
      计算index方法：index = hash & (tab.length – 1)

      put 过程
      Java7 是先扩容后插入新值的，Java8 先插值再扩容，resize() 方法用于初始化数组或数组扩容，
      每次扩容后，容量为原来的 2 倍，并进行数据迁移。

      get 过程
      1.计算 key 的 hash 值，根据 hash 值找到对应数组下标: hash & (length-1)
      2.判断数组该位置处的元素是否刚好就是我们要找的，如果不是，走第三步
      3.判断该元素类型是否是 TreeNode，如果是，用红黑树的方法取数据，如果不是，走第四步。
      PS：Java8 中使用 Node，都是 key，value，hash 和 next 这四个属性，不过，Node 只能用于链表的情况。红黑树的情况需要使用 TreeNode。
      我们根据数组元素中，第一个节点数据类型是 Node 还是 TreeNode 来判断该位置下是链表还是红黑树的。
      4.遍历链表，直到找到相等(==或equals)的 key

      HashMap的初始值还要考虑加载因子:
      哈希冲突：若干Key的哈希值按数组大小取模后，如果落在同一个数组下标上，将组成一条Entry链，
      对Key的查找需要遍历Entry链上的每个元素执行equals()比较。
      加载因子：为了降低哈希冲突的概率，默认当HashMap中的键值对达到数组大小的75%时，即会触发扩容。因此，如果预估容量是100，
      即需要设定100/0.75＝134的数组大小。
      空间换时间：如果希望加快Key查找的时间，还可以进一步降低加载因子，加大初始大小，以降低哈希冲突的概率。
      HashMap和Hashtable都是用hash算法来决定其元素的存储，因此HashMap和Hashtable的hash表包含如下属性：

        容量（capacity）：hash表中桶的数量，默认16
        初始化容量（initial capacity）：创建hash表时桶的数量，HashMap允许在构造器中指定初始化容量
        尺寸（size）：当前hash表中记录的数量
        负载因子（load factor）：负载因子等于“size/capacity”。负载因子为0，表示空的hash表，0.5表示半满的散列表，
            依此类推。轻负载的散列表具有冲突少、适宜插入与查询的特点（但是使用Iterator迭代元素时比较慢）
        除此之外，hash表里还有一个“负载极限”，“负载极限”是一个0～1的数值，“负载极限”决定了hash表的最大填满程度。
            当hash表中的负载因子达到指定的“负载极限”时，hash表会自动成倍地增加容量（桶的数量），并将原有的对象重新分配，放入新的桶内，这称为rehashing。

     HashMap和Hashtable的构造器允许指定一个负载极限，HashMap和Hashtable默认的“负载极限”为0.75，
         这表明当该hash表的3/4已经被填满时，hash表会发生rehashing。“负载极限”的默认值（0.75）是时间和空间成本上的一种折中：

      较高的“负载极限”可以降低hash表所占用的内存空间，但会增加查询数据的时间开销，
      而查询是最频繁的操作（HashMap的get()与put()方法都要用到查询）
      较低的“负载极限”会提高查询数据的性能，但会增加hash表所占用的内存开销，程序猿可以根据实际情况来调整“负载极限”值。

      Hashtable 暂时没有废弃，但是不建议使用，它和HashMap 的区别有：
      1.HashMap几乎可以等价于Hashtable，除了HashMap是非synchronized的，
        并可以接受null(HashMap可以接受为null的键值(key)和值(value)，而Hashtable则不行。
      2.HashMap是非synchronized，而Hashtable是synchronized，这意味着Hashtable是线程安全的，多个线程可以共享一个Hashtable；
        而如果没有正确的同步的话，多个线程是不能共享HashMap的。
        Java 5提供了ConcurrentHashMap，它是HashTable的替代，比HashTable的扩展性更好。
      3.另一个区别是HashMap的迭代器(Iterator)是fail-fast迭代器，而Hashtable的enumerator迭代器不是fail-fast的。
        所以当有其它线程改变了HashMap的结构（增加或者移除元素），将会抛出ConcurrentModificationException，
        但迭代器本身的remove()方法移除元素则不会抛出ConcurrentModificationException异常。
        但这并不是一个一定发生的行为，要看JVM。这条同样也是Enumeration和Iterator的区别。
      4.由于Hashtable是线程安全的也是synchronized，所以在单线程环境下它比HashMap要慢。
        如果你不需要同步，只需要单一线程，那么使用HashMap性能要好过Hashtable。
      5.HashMap不能保证随着时间的推移Map中的元素次序是不变的。

      ConcurrentHashMap
      底层采用分段的数组+链表实现，线程安全
      通过把整个Map分为N个Segment，可以提供相同的线程安全，但是效率提升N倍，默认提升16倍。
      (读操作不加锁，由于HashEntry的value变量是 volatile的，也能保证读取到最新的值。)
      Hashtable的synchronized是针对整张Hash表的，即每次锁住整张表让线程独占，ConcurrentHashMap允许多个修改操作并发进行，
      其关键在于使用了锁分离技术。
      有些方法需要跨段，比如size()和containsValue()，它们可能需要锁定整个表而而不仅仅是某个段，这需要按顺序锁定所有段，
      操作完毕后，又按顺序释放所有段的锁
      扩容：段内扩容（段内元素超过该段对应Entry数组长度的75%触发扩容，不会对整个Map进行扩容），
      插入前检测需不需要扩容，有效避免无效扩容

      Java8 ConcurrentHashMap 源码真心不简单，最难的在于扩容，数据迁移操作不容易看懂。

      put 真心复杂

      get 过程分析
      get 方法从来都是最简单的，这里也不例外：

     1.计算 hash 值
     2.根据 hash 值找到数组对应位置: (n – 1) & h
     3.根据该位置处结点性质进行相应查找
     4.如果该位置为 null，那么直接返回 null 就可以了
     5.如果该位置处的节点刚好就是我们需要的，返回该节点的值即可
     6.如果该位置节点的 hash 值小于 0，说明正在扩容，或者是红黑树，后面我们再介绍 find 方法
     7.如果以上 3 条都不满足，那就是链表，进行遍历比对即可
     */
    private Hashtable<Integer,Integer> hashtable;

    private HashMap<Integer,Integer> hashMap;

    private ConcurrentHashMap<Integer,Integer> concurrentHashMap;


}
