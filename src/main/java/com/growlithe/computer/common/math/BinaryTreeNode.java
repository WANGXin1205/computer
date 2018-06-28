package com.growlithe.computer.common.math;

import java.math.BigDecimal;

/**
 * @Author : Growlithe
 * @Date : 2018/6/7 19:35
 * @Description 二叉树节点
 */
public class BinaryTreeNode {

    /**
     * 根节点
     */
    private Integer root;
    /**
     * 左节点
     */
    private BinaryTreeNode leftNode;
    /**
     * 右节点
     */
    private BinaryTreeNode rightNode;

    public BinaryTreeNode() {
    }

    public BinaryTreeNode(Integer root, BinaryTreeNode leftNode, BinaryTreeNode rightNode) {
        this.root = root;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    /**
     * 先序遍历
     *
     * @param binaryTreeNode
     */
    public static void preOrder(BinaryTreeNode binaryTreeNode) {
        if (binaryTreeNode.getRoot() != null) {
            System.out.println(binaryTreeNode.getRoot());
        }
        if (binaryTreeNode.getLeftNode() != null) {
            var node = binaryTreeNode.getLeftNode();
            preOrder(node);
        }
        if (binaryTreeNode.getRightNode() != null) {
            var node = binaryTreeNode.getRightNode();
            preOrder(node);
        }
    }

    /**
     * 中序遍历
     *
     * @param binaryTreeNode
     */
    public static void inOrder(BinaryTreeNode binaryTreeNode) {
        if (binaryTreeNode.getLeftNode() != null) {
            var node = binaryTreeNode.getLeftNode();
            preOrder(node);
        }
        if (binaryTreeNode.getRoot() != null) {
            System.out.println(binaryTreeNode.getRoot());
        }
        if (binaryTreeNode.getRightNode() != null) {
            var node = binaryTreeNode.getRightNode();
            preOrder(node);
        }
    }

    /**
     * 后序遍历
     *
     * @param binaryTreeNode
     */
    public static void postOrder(BinaryTreeNode binaryTreeNode) {
        if (binaryTreeNode.getLeftNode() != null) {
            var node = binaryTreeNode.getLeftNode();
            preOrder(node);
        }
        if (binaryTreeNode.getRightNode() != null) {
            var node = binaryTreeNode.getRightNode();
            preOrder(node);
        }
        if (binaryTreeNode.getRoot() != null) {
            System.out.println(binaryTreeNode.getRoot());
        }
    }

    /**
     * 二叉树的保存
     *
     * @param nodeValue
     * @param binaryTreeNode
     */
    public static void saveBinaryTree(Integer nodeValue, BinaryTreeNode binaryTreeNode) {
        if (nodeValue.equals(binaryTreeNode.getRoot())) {
            return;
        }
        if (nodeValue < binaryTreeNode.getRoot()) {
            if (binaryTreeNode.getLeftNode() != null) {
                saveBinaryTree(nodeValue, binaryTreeNode.getLeftNode());
            }
            if (binaryTreeNode.getLeftNode() == null) {
                BinaryTreeNode leftBinaryTreeNode = new BinaryTreeNode();
                leftBinaryTreeNode.setRoot(nodeValue);
                binaryTreeNode.setLeftNode(leftBinaryTreeNode);
            }
        }
        if (nodeValue > binaryTreeNode.getRoot()) {
            if (binaryTreeNode.getRightNode() != null) {
                saveBinaryTree(nodeValue, binaryTreeNode.getRightNode());
            }
            if (binaryTreeNode.getRightNode() == null) {
                BinaryTreeNode rightBinaryTreeNode = new BinaryTreeNode();
                rightBinaryTreeNode.setRoot(nodeValue);
                binaryTreeNode.setRightNode(rightBinaryTreeNode);
            }
        }
    }

    public Integer getRoot() {
        return root;
    }

    public void setRoot(Integer root) {
        this.root = root;
    }

    public BinaryTreeNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(BinaryTreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public BinaryTreeNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(BinaryTreeNode rightNode) {
        this.rightNode = rightNode;
    }

    @Override
    public String toString() {
        return "BinaryTreeNode{" +
                "root=" + root +
                ", leftNode=" + leftNode +
                ", rightNode=" + rightNode +
                '}';
    }
}
