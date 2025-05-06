package ClassExamples.Synchronized;

public class SynchronizedExample03 {
    protected Object object = null;
    //sync method
    public synchronized void setObject(Object o) {
        this.object = o;
    }

    public synchronized Object getObject(){
        return this.object;
    }

    //sync block
    public void setObject2(Object o)
    {
        synchronized (this){ // monitor object
            this.object = o;
        }
    }

    public Object getObject2()
    {
        synchronized (this)
        {
            return this.object;
        }
    }
    public static Object staticObject;
    public static synchronized void SetObject(Object o)
    {
        staticObject = o;
    }

    public static void SetObject2(Object o)
    {
        synchronized (SynchronizedExample03.class)
        {
            staticObject = o;
        }
    }
}
