// var. 21654
public class Lab4Fixed {
  public static void main(String[] args) {
    H a = new H();
    H b = new D();
    D c = new D();
    b.t49();
    a.t10();
    a.t27();
    c.t37();
    a.t12();
    b.t45();
    a.t35();
    b.t13();
    a.t8();
    c.t41();
    b.t3();
    a.t19();
    c.t44();
    a.t9();
    b.t17();
    c.t28();
    a.t16(a);
    b.t16(b);
    b.t16(c);
    c.t6();
    c.t24();
    c.t22();
    Thread t = new Thread(new Runnable() {
      public void run() {
        while(true) {
          try {
             D d = new D();
             d.t11();
             Thread.sleep(8);
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
}
class H {
  int t26;
  int t2;
  int t40;
  int t29;
  int t50;
  long t25;
  long t34;
  long t38;
  java.io.ObjectOutputStream sampleOut;
  java.io.ObjectOutputStream helloWorldWriter;
  java.io.ObjectOutputStream fileOut;
  int[] t31 = {3, -3, -3, 2};
  int[] t5 = {1, 2, -3, 0};
  int[] t14 = {1, 0, -3, -2};
  static String stack = "";
  static java.util.Map<java.net.URI,byte[]> cache = new java.util.HashMap<java.net.URI,byte[]>();
  static int t42;
  static int t39;
  static int t7;
  static int t32;
  static int t47;
  java.util.List<String> t21 = new java.util.ArrayList<String>();
  java.util.List<String> t18 = new java.util.ArrayList<String>();
  java.util.List<String> t46 = new java.util.ArrayList<String>();
  public H() {
    t26 = 3;
    t2 = 8;
    t40 = 2;
    t29 = 8;
    t50 = 1;
    t25 = 2L;
    t34 = 1L;
    t38 = 4L;
    try {
        sampleOut = new java.io.ObjectOutputStream(new java.io.FileOutputStream("sampleOut.txt"));
        helloWorldWriter = new java.io.ObjectOutputStream(new java.io.FileOutputStream("helloWorldWriter.txt"));
        fileOut = new java.io.ObjectOutputStream(new java.io.FileOutputStream("fileOut.txt"));
    } catch (java.lang.Exception e) {
      // Do nothing
    }
  }
  public void init() {
    try {
      if (sampleOut == null) sampleOut = new java.io.ObjectOutputStream(new java.io.FileOutputStream("sampleOut.txt"));
      System.out.println("Thread : " + Thread.currentThread() + ", sampleOut = " + sampleOut);
    } catch(Exception e) {
      // Ignore it
    }
    try {
      if (helloWorldWriter == null) helloWorldWriter = new java.io.ObjectOutputStream(new java.io.FileOutputStream("helloWorldWriter.txt"));
      System.out.println("Thread : " + Thread.currentThread() + ", helloWorldWriter = " + helloWorldWriter);
    } catch(Exception e) {
      // Ignore it
    }
    try {
      if (fileOut == null) fileOut = new java.io.ObjectOutputStream(new java.io.FileOutputStream("fileOut.txt"));
      System.out.println("Thread : " + Thread.currentThread() + ", fileOut = " + fileOut);
    } catch(Exception e) {
      // Ignore it
    }
  }
  public byte[] getValueFromCache(String s) {
    try {
      java.net.URI url = new java.net.URI(s);
      if(!cache.containsKey(url)) {
        cache.put(url, new byte[524288]);
      }
      return cache.get(url);
    } catch (Exception e) {
      System.out.println("Error: invalid URL!");
      return null;
    }
  }
  public void t49() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        init();
        int i = 0;
        while(true) {
          i++;
          try {
            synchronized(sampleOut) {
              sampleOut.writeObject("метод t49 в классе H (#" + String.valueOf(i) + ")");
              Thread.sleep(6);
              sampleOut.flush();
              sampleOut.reset();
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public void t10() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        init();
        int i = 0;
        while(true) {
          i++;
          try {
            synchronized(fileOut) {
              fileOut.writeObject("метод t10 в классе H (#" + String.valueOf(i) + ")");
              Thread.sleep(6);
              fileOut.reset();
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public void t27() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        init();
        int i = 0;
        while(true) {
          i++;
          try {
            synchronized(fileOut) {
              fileOut.writeObject("метод t27 в классе H (#" + String.valueOf(i) + ")");
              Thread.sleep(6);
              fileOut.flush();
              fileOut.reset();
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public void t37() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        init();
        int i = 0;
        while(true) {
          i++;
          try {
            synchronized(sampleOut) {
              sampleOut.writeObject("метод t37 в классе H (#" + String.valueOf(i) + ")");
              Thread.sleep(5);
              sampleOut.flush();
              sampleOut.reset();
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public void t12() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        init();
        int i = 0;
        while(true) {
          i++;
          try {
            synchronized(helloWorldWriter) {
              helloWorldWriter.writeObject("метод t12 в классе H (#" + String.valueOf(i) + ")");
              Thread.sleep(5);
              helloWorldWriter.reset();
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public void t45() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        init();
        int i = 0;
        while(true) {
          i++;
          try {
            synchronized(sampleOut) {
              sampleOut.writeObject("метод t45 в классе H (#" + String.valueOf(i) + ")");
              Thread.sleep(6);
              sampleOut.reset();
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public void t44() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        int i = 0;
        while(true) {
          i++;
          try {
            synchronized(stack) {
              stack = stack.substring(0, stack.length() - 1);
              System.gc();
              Thread.sleep(6);
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public void t9() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        int i = 0;
        while(true) {
          i++;
          try {
            synchronized(stack) {
              stack = stack.substring(0, stack.length() - 1);
              System.gc();
              Thread.sleep(6);
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public void t17() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        int i = 0;
        while(true) {
          i++;
          try {
            synchronized(stack) {
              stack = stack.substring(0, stack.length() - 1);
              System.gc();
              Thread.sleep(5);
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public void t28() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        int i = 0;
        while(true) {
          i++;
          try {
            synchronized(stack) {
              stack += "3";
              Thread.sleep(6);
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public void t19() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        int i = 0;
        while(true) {
          i++;
          try {
            synchronized(stack) {
              stack = stack.substring(0, stack.length() - 1);
              System.gc();
              Thread.sleep(6);
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public void t3() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        int i = 0;
        while(true) {
          i++;
          try {
            synchronized(stack) {
              stack += "7";
              Thread.sleep(6);
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public static void t35() {
    System.out.println("метод t35 в классе H");
    System.out.println(t42);
  }
  public static void t13() {
    System.out.println("метод t13 в классе H");
    System.out.println((t42 - 2));
  }
  public static void t8() {
    System.out.println("метод t8 в классе H");
    System.out.println(t39);
  }
  public static void t41() {
    System.out.println("метод t41 в классе H");
    System.out.println((t39 - 4));
  }
  public void t16(H r) {
    r.t49();
  }
  public void t16(D r) {
    r.t10();
  }
}
class D extends H {
  public D() {
    t26 = 3;
    t2 = 5;
    t40 = 4;
    t29 = 9;
    t50 = 6;
    t38 = 8L;
  }
  public void t49() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        init();
        int i = 0;
        while(true) {
          i++;
          try {
            synchronized(sampleOut) {
              sampleOut.writeObject("метод t49 в классе D (#" + String.valueOf(i) + ")");
              Thread.sleep(5);
              sampleOut.flush();
              sampleOut.reset();
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public void t27() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        init();
        int i = 0;
        while(true) {
          i++;
          try {
            synchronized(sampleOut) {
              sampleOut.writeObject("метод t27 в классе D (#" + String.valueOf(i) + ")");
              Thread.sleep(7);
              sampleOut.reset();
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public void t12() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        init();
        int i = 0;
        while(true) {
          i++;
          try {
            synchronized(helloWorldWriter) {
              helloWorldWriter.writeObject("метод t12 в классе D (#" + String.valueOf(i) + ")");
              Thread.sleep(6);
              helloWorldWriter.reset();
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public void t45() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        init();
        int i = 0;
        while(true) {
          i++;
          try {
            synchronized(fileOut) {
              fileOut.writeObject("метод t45 в классе D (#" + String.valueOf(i) + ")");
              Thread.sleep(6);
              fileOut.reset();
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public void t6() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        while(true) {
          try {
            synchronized(cache) {
              getValueFromCache("https://www.google.com");
              Thread.sleep(11);
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public void t24() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        while(true) {
          try {
            synchronized(cache) {
              getValueFromCache("https://www.google.com");
              Thread.sleep(8);
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public void t22() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        while(true) {
          try {
            synchronized(cache) {
              getValueFromCache("https://www.google.com");
              Thread.sleep(12);
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public static void t35() {
    System.out.println("метод t35 в классе D");
    System.out.println(++t42);
  }
  public static void t13() {
    System.out.println("метод t13 в классе D");
    System.out.println(t39);
  }
  public static void t8() {
    System.out.println("метод t8 в классе D");
    System.out.println((t39 - 3));
  }
  public static void t41() {
    System.out.println("метод t41 в классе D");
    System.out.println(t39);
  }
  public void t36() {
    for(int i = 0; i < 9; i++) {
      this.t21.add(String.valueOf(System.nanoTime()));
      // System.out.println(this.t21.size());
    }
  }
  public void t23() {
    for(int i = 0; i < 7; i++) {
      this.t18.add(String.valueOf(System.nanoTime()));
      // System.out.println(this.t18.size());
    }
  }
  public void t33() {
    for(int i = 0; i < 5; i++) {
      this.t46.add(String.valueOf(System.nanoTime()));
      // System.out.println(this.t46.size());
    }
  }
  public void t11() {
    for(int i = 0; i < 9; i++) {
      this.t46.add(String.valueOf(System.nanoTime()));
      // System.out.println(this.t46.size());
    }
  }
  public void t16(H r) {
    r.t27();
  }
  public void t16(D r) {
    r.t37();
  }
}
