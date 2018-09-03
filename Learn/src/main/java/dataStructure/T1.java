package dataStructure;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 链表
 */
public class T1 {

    @Test
    public void Test0() {
        ListNode l1 = new ListNode(6);
        ListNode l2 = new ListNode(1);
        l1.next = l2;
        ListNode l3 = new ListNode(7);
        l2.next = l3;

        ListNode r1 = new ListNode(2);
        ListNode r2 = new ListNode(9);

        r1.next = r2;
        ListNode r3 = new ListNode(5);
        r2.next = r3;

        T1 t1 = new T1();
        ListNode listNode = t1.addLists2(l1, r1);

        System.out.print(listNode.val);
        while (listNode.next != null) {
            System.out.print("->");
            System.out.print(listNode.next.val);
            listNode = listNode.next;
        }

    }

    public ListNode addLists2(ListNode l1, ListNode l2) {
        String l = tranNodeToInt(l1);
        String r = tranNodeToInt(l2);
        ListNode node = tranIntToNode(addStrings(l, r));
        return node;
    }

    public String tranNodeToInt(ListNode node) {
        if (node == null) {
            return "0";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(node.val);
        while (node.next != null) {
            builder.append(node.next.val);
            node = node.next;
        }
        return builder.toString();
    }

    public ListNode tranIntToNode(String sum) {
        ListNode node = new ListNode(-2);
        String string = sum;
        ListNode temp = new ListNode(-1);
        node = temp;

        for (int i = 0; i < string.length(); i++) {
            ListNode listNode = new ListNode(Integer.parseInt(string.charAt(i) + ""));
            temp.next = listNode;
            temp = listNode;
        }
        return node.next;
    }

    public String addStrings(String num1, String num2) {
        if (num1.length() > num2.length()) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < num1.length() - num2.length(); i++) {
                stringBuffer.append(0);
            }
            stringBuffer.append(num2);
            num2 = stringBuffer.toString();
        }
        if (num1.length() < num2.length()) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < num2.length() - num1.length(); i++) {
                stringBuffer.append(0);
            }
            stringBuffer.append(num1);
            num1 = stringBuffer.toString();
        }

        List<Integer> list = new ArrayList<>();
        for (int i = num1.length() - 1; i >= 0; i--) {
            int temp = Integer.parseInt(num1.charAt(i) + "") + Integer.parseInt(num2.charAt(i) + "");
            list.add(temp);
        }
        list.add(0);
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) >= 10) {
                list.set(i + 1, list.get(i) / 10 + list.get(i + 1));
                list.set(i, list.get(i) % 10);
            }
        }
        if (list.get(list.size() - 1) == 0) {
            list.remove(list.size() - 1);
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = list.size() - 1; i >= 0; i--) {
            stringBuffer.append(list.get(i));
        }
        return stringBuffer.toString();
    }


    @Test
    public void Test1() {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        l1.next = l2;
        ListNode l3 = new ListNode(3);
        l2.next = l3;
        ListNode l4 = new ListNode(4);
        l3.next = l4;
        ListNode listNode = swapNodes(l1, 2, 4);
        System.out.print(listNode.val);
        while (listNode.next != null) {
            System.out.print("->");
            System.out.print(listNode.next.val);
            listNode = listNode.next;
        }
    }

    public ListNode swapNodes(ListNode head, int v1, int v2) {
        if (head == null) return null;
        ListNode retNode = new ListNode(-1);
        retNode.next = head;

        int count = 2;
        ListNode sw1p = null, sw2p = null;
        if (head.val == v1) {
            sw1p = head;
            count--;
        }
        if (head.val == v2) {
            sw2p = head;
            count--;
        }

        boolean sw1pFirst = true;
        while (count != 0) {
            if (head.next == null) break;
            if (head.next.val == v1 && sw1p == null) {
                sw1p = head;
                if (sw2p == null) sw1pFirst = true;
                count--;
            }
            if (head.next.val == v2 && sw2p == null) {
                sw2p = head;
                if (sw1p == null) sw1pFirst = false;
                count--;
            }
            head = head.next;
        }

        if (!sw1pFirst) {
            ListNode temp = sw1p;
            sw1p = sw2p;
            sw2p = temp;
        }

        if (sw1p != null && sw2p != null) {
            ListNode node2Next = sw2p.next.next;
            sw1p.next = sw2p.next;
            sw2p.next.next = sw1p.next.next;
            sw2p.next = sw1p.next;
            sw2p.next.next = node2Next;
        }
        return retNode.next;
    }

}
