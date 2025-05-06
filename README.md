# Important Note:
This project is configured to use a **local Gradle installation**. If you're opening this project on your own system, please make sure to:

1. Go to **Settings/Preferences** > **Build, Execution, Deployment** > **Build Tools** > **Gradle**.

2. Under **Gradle settings**, change the **Gradle distribution** to:

- **Use local Gradle distribution**, and

- Set the **Gradle home** path to your own local Gradle installation directory.

If you don’t have Gradle installed locally, you can either:

- Install Gradle manually and configure the path, or

- Change the setting to **Use Gradle wrapper** instead.



# Class Examples

## Race Condition Example
### Learning Goals
- **Understand the concept of race conditions in multithreaded programming.**
    - A race condition occurs when multiple threads try to access and modify shared data concurrently, and the final outcome depends on the timing of thread execution.

- **Identify the problem of shared mutable state (`counter` in this case) being updated by multiple threads without synchronization.**
## Lock Examples
### Learning Goals

- **Understand the purpose and usage of `Lock` and `ReentrantLock` for thread synchronization.**

- **Identify and implement a critical section using explicit locking.**

- **Observe thread behavior when competing for a shared resource.**

- **understand how to pass parameters to threads using custom `Runnable` implementations.**

- **Understand the concept of a _fair lock_ using `ReentrantLock(true)` and its scheduling implications.**

- **Learn how `ReentrantLock` allows the same thread to acquire the lock multiple times (reentrancy).**

- **Use and differentiate between `lock()` and `tryLock()` with and without a timeout.**

- **Practice inspecting lock state using methods like `isLocked()`, `getHoldCount()`, and `isHeldByCurrentThread()`.**

## Deadlock Example
### Learning Goals
- **Understand the concept of deadlock in multithreaded programming.**

- **Learn how holding one lock and waiting for another can result in a deadlock situation.**

- **Explore how the order of acquiring locks can lead to contention and deadlock.**


## `synchronized` Examples
### Learning Goals
- **Understand the concept of synchronization in multithreading:**

    - The `synchronized` keyword is used to prevent multiple threads from accessing the same critical section of code at the same time, which ensures thread safety and prevents race conditions.

- **Learn how to synchronize access to shared resources:**

    - The `PrintDemo` class is a shared resource between the two threads (`ThreadDemo`). The synchronization ensures that only one thread at a time can access the `printCount()` method, which is the critical section in this example.

- **Understand the difference between synchronized methods and synchronized blocks:**

    - Synchronized methods, like `setObject()` and `getObject()`, ensure that only one thread can execute the method at a time on a given instance of the class. In contrast, synchronized blocks, like in `setObject2()` and `getObject2()`, allow fine-grained control over synchronization by locking only specific sections of the method (in this case, the block that manipulates the `object`).

- **Learn how to synchronize instance methods using `synchronized` keyword:**

    - The methods `setObject()` and `getObject()` are synchronized on the instance (`this`), ensuring that only one thread can access them at a time for a specific object.

- **Examine how synchronization helps prevent race conditions and ensures thread safety:**

    - By synchronizing methods and blocks, we prevent race conditions where multiple threads might try to update shared resources (`object` or `staticObject`) concurrently, which could lead to inconsistent results.

- **Understand the concept of using `synchronized` for both instance-level and class-level resources:**
    - Instance-level synchronization is applied using `this` (the instance object), whereas class-level synchronization is applied using the class itself (`TheClass.class`).

## Semaphore Example
### Learning Goals
- **Understand the concept of semaphores and how they control access to shared resources:**

    - The example demonstrates how semaphores are used to limit the number of threads accessing a shared resource. In this case, the semaphore is initialized with 2 permits, allowing at most 2 threads to enter the critical section simultaneously.

- **Learn how to use `Semaphore.acquire()` and `Semaphore.release()`:**

    - `acquire()` is used to request a permit. If no permits are available, the calling thread will block until one becomes available.

    - `release()` is used to release a permit, making it available for other threads.

- **Explore the role of semaphores in controlling concurrency and preventing resource contention:**

    - Semaphores are useful when you want to limit the number of threads accessing a specific resource (in this case, the simulated resource), preventing too many threads from causing contention or overload.

# Workshop

You should complete the following classes, located in `Workshop` folder.

## `LockWorkshop`

- [ ] Prevent race condition from happening using `ReentrantLock`
- [ ]  Run the program to confirm that the final value of `counter` is exactly 2,000,000 (1,000,000 increments per thread).

## `SynchronizedWorkshop`
- [ ] Prevent race condition from happening using `synchronized`
- [ ]  Run the program to confirm that the final value of `counter` is exactly 2,000,000 (1,000,000 increments per thread).

## `DeadlockPreventionWorkshop`

- [ ] Prevent deadlock (hint: if you make sure that all locks are always taken in the same order by any thread, deadlocks cannot occur in this example)
- [ ]  Test that both threads run to completion without getting stuck (no deadlock).

## `TaylorSeries`
- [ ] Search for the Taylor series of sin(x).
- [ ] Read this article to get to know BigDecimal in Java [Mastering Big Decimals in Java](https://solutionsarchitecture.medium.com/mastering-big-decimals-in-java-understanding-implementation-performance-and-alternatives-7e3a3a96efc6#:~:text=What%20is%20Big%20Decimal%3F,any%20desired%20level%20of%20accuracy.)
- [ ] Implement the `factorial(int n)` method using `BigDecimal`.
- [ ] In the `run()` method, calculate the n-th term of the Taylor series for `sin(x)`:
- [ ] Add the calculated term to the shared variable `sum`.
- [ ] Compare the final result with the accurate pre-calculated value of `sin(0.01)`.
- [ ] Use `toPlainString()` to print `BigDecimal` values without scientific notation.
