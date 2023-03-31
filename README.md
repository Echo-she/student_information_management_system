# Student Information Management System

#### 学生信息管理系统

> 使用Java+Python实现（Python部分已经打包为exe，使用Java调用）

这是本人学习Java时的课设，现已开源，有需要的兄弟自取，保证可以运行，如果对你有帮助的话，给个star吧

现本人已转Python，主要方向为人工智能、深度学习、前后端，有毕设需要的可以加QQ：775075365

**系统目标：**

1. 使用窗口程序对学生信息进行添加，更改，删除，查询操作，将学生信息存入student_information数据库中的student表。
2. 规定管理员信息，将其信息存入student_information数据库中的user表
3. 在登录界面中对登录的用户名，密码进行合法性判断，对于不在数据库中的用户进行拒绝进入管理员界面处理。当然也可以使用注册功能成为管理员。
4. 可以在系统中将全部学生信息导出为txt，xlsx，xls等格式，在管理员界面中可以修改管理员密码
5. 系统还提供了检查数据库连接情况的功能，统计学生信息并将其可视化处理，展示在窗口中

**需求分析**

1. 采用新的学生信息管理系统可取代原系统的手工管理工作，减少人工开支，节省资金，并且可大大提高数据信息的取得，缩短数据信息处理时间，提高学生信息的利用率，使教学质量更进了一个台阶。
2. 本系统操作简单，易于理解，只需通过简单指导,上手较快，系统管理员、教师以及学生均能进行操作，运行环境要求低。
3. 本系统在 Windows 10开发环境下，利用java语言开发工具，系统可移植性强，安装过程简单，并使用mysql作为数据库管理系统，来实现学生信息管理系统的各种功能。服务器本身重在数据的管理，为客户端应用程序提供一致的接口。开发工具也可以是它的更高版，这样可以提高系统的性能，降低它的局限性。

**功能需求说明**

1. 管理员可以添加学生信息，更改学生信息，删除学生信息，查询学生信息，测试数据库连接情况，查看全部学生信息。

- 查询学生信息提供按姓名查询，按专业查询，按班级查询。

- 添加学生信息中的性别只能在男，女中选其一，

- 添加学生信息中的专业只能在计算机专业、软件工程、会计学、金融学、财政学、工程造价中选其一。

2. 系统登录中将对用户的合法性进行验证，能通过注册功能将新管理员信息添加到student_information数据库的user表中，其中还将检查用户名是否在数据库中已经存在，2次密码是否相同。

3. 系统提供在查看全部学生信息窗口中导出学生信息，格式可以为txt，csv，xlsx，xls等等。

4. 系统提供自动统计学生信息，并将其可视化，目前只支持按班级，按性别，按专业，按出生日期，按入学年度进行扇形图，柱状图展示。

**开发环境**

IntelliJ IDEA 2020.1

jdk 1.8

mysql 5.6.49

Navicat 15.0.17

**功能结构图**

![](https://gitee.com/unlucky-she/student_information_management_system/raw/master/image/picture1.png)