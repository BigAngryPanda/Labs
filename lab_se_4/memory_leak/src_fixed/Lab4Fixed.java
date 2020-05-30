// var. 11111114
public class Lab4Fixed {
  public static void main(String[] args) {
    D a = new D();
    D b = new E();
    E c = new E();
    a.x15();
    b.x23();
    b.x7();
    c.x31();
    c.x18();
    a.x50();
    c.x26();
    b.x30();
    b.x25();
    b.x34();
    b.x20(a);
    c.x20(b);
    b.x20(c);
    c.x33();
    c.x39();
    c.x35();
    Thread t = new Thread(new Runnable() {
      public void run() {
        while(true) {
          try {
             E d = new E();
             d.x41();
             //Thread.sleep(9);
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
}
class D {
  int x45;
  int x22;
  int x44;
  int x10;
  int x17;
  long x19;
  long x13;
  long x21;
  java.io.ObjectOutputStream stringsOutput;
  java.io.ObjectOutputStream stringsWriter;
  java.io.ObjectOutputStream helloWorldOutput;
  int[] x8 = {-3, 1, 0, -2, 3};
  int[] x49 = {-2, 0, 3, 0, 3};
  int[] x32 = {-2, 1, -2, -1};
  // static java.util.Map<java.net.URL,byte[]> cache = new java.util.HashMap<java.net.URL,byte[]>();
  static java.util.Map<String,byte[]> cache = new java.util.HashMap<String,byte[]>();
  static int x46;
  static int x4;
  static int x38;
  static int x5;
  static int x36;
  java.util.List<String> x47 = new java.util.ArrayList<String>();
  java.util.List<String> x1 = new java.util.ArrayList<String>();
  java.util.List<String> x11 = new java.util.ArrayList<String>();
  public D() {
    x45 = 7;
    x22 = 5;
    x44 = 2;
    x10 = 3;
    x17 = 9;
    x19 = 7L;
    x13 = 3L;
    x21 = 3L;
    try {
        stringsOutput = new java.io.ObjectOutputStream(new java.io.FileOutputStream("stringsOutput.txt"));
        stringsWriter = new java.io.ObjectOutputStream(new java.io.FileOutputStream("stringsWriter.txt"));
        helloWorldOutput = new java.io.ObjectOutputStream(new java.io.FileOutputStream("helloWorldOutput.txt"));
    } catch (java.lang.Exception e) {
      // Do nothing
    }
  }
  public void init() {
    try {
      if (stringsOutput == null) stringsOutput = new java.io.ObjectOutputStream(new java.io.FileOutputStream("stringsOutput.txt"));
      System.out.println("Thread : " + Thread.currentThread() + ", stringsOutput = " + stringsOutput);
    } catch(Exception e) {
      // Ignore it
    }
    try {
      if (stringsWriter == null) stringsWriter = new java.io.ObjectOutputStream(new java.io.FileOutputStream("stringsWriter.txt"));
      System.out.println("Thread : " + Thread.currentThread() + ", stringsWriter = " + stringsWriter);
    } catch(Exception e) {
      // Ignore it
    }
    try {
      if (helloWorldOutput == null) helloWorldOutput = new java.io.ObjectOutputStream(new java.io.FileOutputStream("helloWorldOutput.txt"));
      System.out.println("Thread : " + Thread.currentThread() + ", helloWorldOutput = " + helloWorldOutput);
    } catch(Exception e) {
      // Ignore it
    }
  }
  /*
  public byte[] getValueFromCache(String s) {
    try {
      java.net.URL url = new java.net.URL(s);
      if(!cache.containsKey(url)) {
        cache.put(url, new byte[1572864]);
      }
      return cache.get(url);
    } catch (Exception e) {
      System.out.println("Error: invalid URL!");
      return null;
    }
  }
  */
  public byte[] getValueFromCache(String s) {
    try {
      java.net.URL url = new java.net.URL(s);
      url = null;

      if(!cache.containsKey(s)) {
        cache.put(s, new byte[1572864]);
      }
      return cache.get(s);
    } catch (Exception e) {
      System.out.println("Error: invalid URL!");
      return null;
    }
  }
  public void x15() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        init();
        int i = 0;
        while(true) {
          i++;
          try {
            synchronized(helloWorldOutput) {
              helloWorldOutput.writeObject("метод x15 в классе D (#" + String.valueOf(i) + ")");
              //Thread.sleep(6);
              helloWorldOutput.flush();
              helloWorldOutput.reset();
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public void x23() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        init();
        int i = 0;
        while(true) {
          i++;
          try {
            synchronized(helloWorldOutput) {
              helloWorldOutput.writeObject("метод x23 в классе D (#" + String.valueOf(i) + ")");
              //Thread.sleep(6);
              //helloWorldOutput.flush();
              helloWorldOutput.reset();
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public void x7() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        init();
        int i = 0;
        while(true) {
          i++;
          try {
            synchronized(helloWorldOutput) {
              helloWorldOutput.writeObject("метод x7 в классе D (#" + String.valueOf(i) + ")");
              //Thread.sleep(5);
              helloWorldOutput.flush();
              helloWorldOutput.reset();
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public void x31() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        init();
        int i = 0;
        while(true) {
          i++;
          try {
            synchronized(stringsOutput) {
              stringsOutput.writeObject("метод x31 в классе D (#" + String.valueOf(i) + ")");
              //Thread.sleep(6);
              stringsOutput.flush();
              stringsOutput.reset();
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public void x18() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        init();
        int i = 0;
        while(true) {
          i++;
          try {
            synchronized(stringsWriter) {
              stringsWriter.writeObject("метод x18 в классе D (#" + String.valueOf(i) + ")");
              //Thread.sleep(6);
              stringsWriter.flush();
              stringsWriter.reset();
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public void x50() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        init();
        int i = 0;
        while(true) {
          i++;
          try {
            synchronized(helloWorldOutput) {
              helloWorldOutput.writeObject("метод x50 в классе D (#" + String.valueOf(i) + ")");
              //Thread.sleep(6);
              helloWorldOutput.flush();
              helloWorldOutput.reset();
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public static void x26() {
    System.out.println("метод x26 в классе D");
    System.out.println(x46);
  }
  public static void x30() {
    System.out.println("метод x30 в классе D");
    System.out.println((x46 - 1));
  }
  public static void x25() {
    System.out.println("метод x25 в классе D");
    System.out.println(x4);
  }
  public static void x34() {
    System.out.println("метод x34 в классе D");
    System.out.println((x4 - 1));
  }
  public void x20(D r) {
    r.x15();
  }
  public void x20(E r) {
    r.x23();
  }
}
class E extends D {
  public E() {
    x22 = 0;
    x44 = 8;
    x10 = 0;
    x13 = 2L;
  }
  public void x31() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        init();
        int i = 0;
        while(true) {
          i++;
          try {
            synchronized(helloWorldOutput) {
              helloWorldOutput.writeObject("метод x31 в классе E (#" + String.valueOf(i) + ")");
              //Thread.sleep(7);
              helloWorldOutput.flush();
              helloWorldOutput.reset();
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public void x33() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        while(true) {
          try {
            synchronized(cache) {
              getValueFromCache("https://www.google.com");
              //Thread.sleep(14);
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public void x39() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        while(true) {
          try {
            synchronized(cache) {
              getValueFromCache("https://www.google.com");
              //Thread.sleep(6);
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public void x35() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        while(true) {
          try {
            synchronized(cache) {
              getValueFromCache("https://www.google.com");
              //Thread.sleep(5);
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public static void x26() {
    System.out.println("метод x26 в классе E");
    System.out.println(--x46);
  }
  public static void x30() {
    System.out.println("метод x30 в классе E");
    System.out.println(x4);
  }
  public static void x25() {
    System.out.println("метод x25 в классе E");
    System.out.println((x4 - 4));
  }
  public static void x34() {
    System.out.println("метод x34 в классе E");
    System.out.println(x4);
  }
  public void x14() {
    for(int i = 0; i < 9; i++) {
      this.x1.add(String.valueOf(System.nanoTime()));
      // System.out.println(this.x1.size());
    }
  }
  public void x16() {
    for(int i = 0; i < 9; i++) {
      this.x11.add(String.valueOf(System.nanoTime()));
      // System.out.println(this.x11.size());
    }
  }
  public void x29() {
    for(int i = 0; i < 8; i++) {
      this.x47.add(String.valueOf(System.nanoTime()));
      // System.out.println(this.x47.size());
    }
  }
  public void x41() {
    for(int i = 0; i < 6; i++) {
      this.x47.add(String.valueOf(System.nanoTime()));
      // System.out.println(this.x47.size());
    }
  }
  public void x20(D r) {
    r.x7();
  }
  public void x20(E r) {
    r.x31();
  }
}