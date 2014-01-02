package chapter3StackAndQueues;

import java.util.LinkedList;

class Animal {
  int order = 0;
  String name;

  public Animal(String name, int order) {
    this.name = name;
    this.order = order;
  }
  
  public boolean isOrderThan(Animal o){
    return this.order > o.order;
  }
}

class Dog extends Animal {
  public Dog(String name, int order) {
    super(name, order);
  }
}

class Cat extends Animal {
  public Cat(String name, int order) {
    super(name, order);
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