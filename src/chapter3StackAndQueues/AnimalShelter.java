package chapter3StackAndQueues;

public class AnimalShelter {
  private static int order = 0;
  MyQueue<Dog> dogs = new MyQueue<Dog>();
  MyQueue<Cat> cats = new MyQueue<Cat>();

  public void enqueue(Animal a) {
    a.order = ++order;
    if (a instanceof Dog) {
      dogs.enqueue(a);
    } else {
      cats.enqueue(a);
    }
  }

  public Dog dequeueDog() {
    return (Dog) dogs.dequeue();
  }

  public Cat dequeueCat() {
    return (Cat) cats.dequeue();
  }

  public Animal dequeueAny() {
    if (dogs.peek().order < cats.peek().order) {
      return dogs.dequeue();
    } else {
      return cats.dequeue();
    }
  }

  public static void main(String... args){
    AnimalShelter as = new AnimalShelter();
    as.enqueue(new Dog("dog1"));
    as.enqueue(new Dog("dog2"));
    as.enqueue(new Dog("dog3"));
    as.enqueue(new Dog("dog4"));
    as.enqueue(new Cat("cat1"));
    as.enqueue(new Cat("cat2"));
    as.enqueue(new Cat("cat3"));
    as.enqueue(new Cat("cat4"));
    System.out.println(as.dequeueAny());
    System.out.println(as.dequeueCat());
    System.out.println(as.dequeueCat());
    System.out.println(as.dequeueAny());
    System.out.println(as.dequeueDog());
    
  }
  
}
