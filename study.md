# Ioc

## 简单的git指令

1. 创建新分支

   ```cobol
    git branch simple-bean-container
   ```

2. 切换分支

   ```cobol
   git checkout simple-bean-container
   ```

3. 加载全部文件

   ```cobol
   git add .
   ```

4. 提交到仓库

   ```cobol
   git commit -m "提交的信息"
   ```

5. 推送

   ```cobol
   git push 分支名称
   got push(主分支)
   ```

6. 合并分支

   首先切换到要合并的分支上

   ```cobol
   git merge 需要合并的分支
   ```

 ## 最简单的bean容器

 > 分支：simple-bean-container

1. 创建一个简单的BeanFactory工厂类，用于管理bean，只有注册bean和获取bean两个成员方法

   ```java
   /**
    * 一个简单的bean工厂
    */
   public class BeanFactory {
       // 简单的 map 集合 管理 bean
       private final Map<String, Object> beanMap = new HashMap<>();
   
       /**
        * 注册bean
        *
        * @param beanName bean的名称
        * @param bean     bean对象
        */
       public void registerBean(String beanName, Object bean) {
           beanMap.put(beanName, bean);
       }
   
       /**
        * 根据bean名称获取bean
        *
        * @param beanName bean的名称
        * @return bean对象
        */
       public Object getBean(String beanName) {
           return beanMap.get(beanName);
       }
   }
   ```

2. 创建一个test类，用于测试BeanFactory对象是否可以正常工作

   ```java
   public class SimpleBeanContainerTest {
       @Test
       public void testGetBean() {
           // 1、获取BeanFactory对象
           BeanFactory beanFactory = new BeanFactory();
           // 2、注册bean、
           beanFactory.registerBean("helloService", new HelloService());
           // 3、获取bean对象
           HelloService helloService = (HelloService) beanFactory.getBean("helloService");
           // 4、使用bean对象的成员方法
           helloService.sayHello();
       }
   
       static class HelloService {
           public void sayHello() {
               System.out.println("hello spring");
           }
       }
   }
   ```
