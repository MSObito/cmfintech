package com.example.obito.model;

import com.example.obito.listviewtest.Fruit;
import com.example.obito.listviewtest.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by obito on 2017/12/14.
 */

public class FruitModel {
    private List<Fruit> fruitList=new ArrayList<>();
    public FruitModel(){
        for(int i=0;i<2;i++){
            Fruit apple=new Fruit(getRandomLengthName("Apple"), R.drawable.apple_pic);
            fruitList.add(apple);
            Fruit banana=new Fruit(getRandomLengthName("Banana"),R.drawable.banana_pic);
            fruitList.add(banana);
            Fruit orange=new Fruit(getRandomLengthName("Orange"),R.drawable.orange_pic);
            fruitList.add(orange);
            Fruit watermelon=new Fruit(getRandomLengthName("Watermelon"),R.drawable.watermelon_pic);
            fruitList.add(watermelon);
            Fruit pear=new Fruit(("Pear"),R.drawable.pear_pic);
            fruitList.add(pear);
            Fruit grape=new Fruit(("Grape"),R.drawable.grape_pic);
            fruitList.add(grape);
            Fruit pineapple=new Fruit(getRandomLengthName("Pineapple"),R.drawable.pineapple_pic);
            fruitList.add(pineapple);
            Fruit strawberry=new Fruit(getRandomLengthName("Strawberry"),R.drawable.strawberry_pic);
            fruitList.add(strawberry);
            Fruit cherry=new Fruit(getRandomLengthName("Cherry"),R.drawable.cherry_pic);
            fruitList.add(cherry);
            Fruit mango=new Fruit(getRandomLengthName("Mango"),R.drawable.mango_pic);
            fruitList.add(mango);
        }
    }

    private String getRandomLengthName(String name){
        Random random=new Random();
        int length=random.nextInt(20)+1;
        StringBuilder builder=new StringBuilder();
        for(int i=0;i<length;i++){
            builder.append(name);
        }
        return builder.toString();
    }

    public List<Fruit> GetFruitList(){
        return fruitList;
    }
}
