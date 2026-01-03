package shopping.entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ShoppingCar extends HashMap<Book,Integer> {
    //购物车添加书籍
    public void add(Book book,int num) throws Exception {
        //判断库存
        if(book.getNumber() < num){
            throw new Exception("编号为："+book.getBid()+"书名为："+ book.getName()+"的书籍，库存仅剩"+ book.getNumber()+"本。请修改你的购买数量！");
        }else {
            if(this.containsKey(book)){//已经有这本书就只增加书的数量
                int newNum = this.get(book)+num;
                this.replace(book,newNum);
            }else {
                this.put(book,num);
            }
        }
        //修改库存
        book.setNumber(book.getNumber() - num);
    }

    //购物车删除数据
    public void delete(Book book) throws Exception {
        if(this.containsKey(book)){
            book.setNumber(book.getNumber() + this.get(book));
            this.remove(book);
        }else {
            throw new Exception("编号为："+book.getBid()+"书名为："+ book.getName()+"的书籍不存在。请修改你要删除的");
        }
    }

    //统计商品数量
    public int countBooksNum(){
        int count = 0;
        Iterator<Integer> iter = this.values().iterator();//获取本数集合的迭代器
        while(iter.hasNext()){
            count += iter.next();
        }
        return count;
    }

    //计算总价
    public float countBooksPrice(){
        float totalPrice = 0;
        Iterator<Map.Entry<Book,Integer>> iter = this.entrySet().iterator();
        Map.Entry<Book,Integer> entry;
        while(iter.hasNext()){
            entry = iter.next();
            totalPrice += entry.getValue() *  entry.getKey().getPrice();
        }
        return totalPrice;
    }
}
