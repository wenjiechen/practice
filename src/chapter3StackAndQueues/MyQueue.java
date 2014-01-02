package chapter3StackAndQueues;

import java.util.LinkedList;

abstract class Animal {
  int order = 0;
  String name;

  public Animal(String name) {
    this.name = name;
  }
  
  public boolean isOrderThan(Animal o){
    return this.order > o.order;
  }
}

class Dog extends Animal {
  public Dog(String name) {
    super(name);
  }
  
  @Override
  public String toString(){
    return "order "+ order + ", name: " + name;
  }
}

class Cat extends Animal {
  public Cat(String name) {
    super(name);
  }
  
  @Override
  public String toString(){
    return "order "+ order + ", name: " + name;
  }

}

public class MyQueue<T extends Animal>{
  LinkedList<Animal> animals = new LinkedList<Animal>();
  
  public void enqueue(Animal a){
    animals.add(a);
  }
  
  public Animal dequeue(){
    Animal ret = animals.getFirst();
    animals.removeFirst();
    return ret;
    
  }
  
  public Animal peek(){
    return animals.getFirst();
  }
  
}