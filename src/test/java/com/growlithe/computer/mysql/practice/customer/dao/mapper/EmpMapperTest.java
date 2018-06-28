package com.growlithe.computer.mysql.practice.customer.dao.mapper;

import com.growlithe.computer.common.math.BinaryTreeNode;
import com.growlithe.computer.mysql.practice.customer.dao.domain.EmpDO;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.plaf.basic.BasicIconFactory;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @Author : Growlithe
 * @Date : 2018/6/3 20:34
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmpMapperTest {

    @Autowired
    private EmpMapper empMapper;

    @Test
    public void listAllEmpTest(){
        var empDOList = empMapper.listAllEmp();
        Assert.assertNotNull(empDOList);
    }

    @Test
    public void saveBatchTest() {
        List<EmpDO> empDOList = new ArrayList<>();
        for (int i = 0; i<1000000 ; i++){
            var empDO = new EmpDO();
            Random random = new Random();
            empDO.setAge(random.nextInt(105));
            empDOList.add(empDO);
        }
        empMapper.saveBatch(empDOList);
    }

    @Test
    public void updateAgeByIdListTest(){
       EmpDO empDO = new EmpDO();
       empDO.setId(5L);
       empDO.setAge(99);
       EmpDO empDO1 = new EmpDO();
       empDO1.setId(8L);
       empDO1.setAge(103);
       List<EmpDO> empDOList = new ArrayList<>();
       empDOList.add(empDO);
       empDOList.add(empDO1);
       Integer integer = empMapper.updateAgeByIdList(empDOList);
       System.out.println(integer);
     }

    @Test
    public void sparkDemoTest(){
//        SparkConf conf=new SparkConf();
//        conf.set("spark.testing.memory", "2147480000"); //因为jvm无法获得足够的资源
//        conf.setAppName("mySpark");
//        conf.setMaster("local");
//
////        JavaSparkContext sc = new JavaSparkContext("spark://127.0.0.1:3306", "First Spark App",conf);
//        JavaSparkContext sc = new JavaSparkContext(conf);
//        System.out.println(sc);
//
//        var empList = empMapper.listEmp();

//        sc.parallelize()

//        BinaryTreeNode J = new BinaryTreeNode(8, null, null);
//        BinaryTreeNode H = new BinaryTreeNode(4, null, null);
//        BinaryTreeNode G = new BinaryTreeNode(2, null, null);
//        BinaryTreeNode F = new BinaryTreeNode(7, null, J);
//        BinaryTreeNode E = new BinaryTreeNode(5, H, null);
//        BinaryTreeNode D = new BinaryTreeNode(1, null, G);
//        BinaryTreeNode C = new BinaryTreeNode(9, F, null);
//        BinaryTreeNode B = new BinaryTreeNode(3, D, E);
//        BinaryTreeNode A = new BinaryTreeNode(6, B, C);
//        System.out.println(A);

//        BinaryTreeNode.preOrder(A);
//        BinaryTreeNode.inOrder(A);
//        BinaryTreeNode.postOrder(A);

//        Integer[] integers = new Integer[]{8,5,7,6,4,3,1,2,9};
//
//        var binaryTreeNode = new BinaryTreeNode();
//        binaryTreeNode.setRoot(integers[0]);
//        for (int i=1 ; i<integers.length;i++){
//           BinaryTreeNode.saveBinaryTree(integers[i],binaryTreeNode);
//        }
//
//        System.out.println(binaryTreeNode);


        var concurrentHashMap = new ConcurrentHashMap<>();

        EmpDO empDO = new EmpDO(1L,3L);
        var empDO1 = new EmpDO(2L,3L);
        var empDO2 = new EmpDO(3L,4L);
        var empDO3 = new EmpDO(4L,null);
        var empDO4 = new EmpDO(5L,6L);
        var empDO5 = new EmpDO(6L,null);
        var empDO6 = new EmpDO(7L,null);
        List<EmpDO> empDOList = Lists.newArrayList(empDO,empDO1,empDO2,empDO3,empDO4,empDO5,empDO6);

        HashMap<Long,Long> empHashMap = new HashMap<>();
        empDOList.forEach(x->empHashMap.put(x.getId(),x.getParentId()));

        for (EmpDO x:empDOList){
            Long id = x.getId();
            while (id != null){
                System.out.print(id);
                id = empHashMap.get(id);
            }
            System.out.println("\n====");
        }

    }



}