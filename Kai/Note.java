1.
  初始化方法注解：
  @PostConstruct
  publicvoidinit1(){
  System.out.println(pisCloudApiKey);
  }

2.
  Object类型的空值null，转换成Boolean类型后仍为null，不是true或者false，不能用于三元表达式

3.
  MyBatis中like的用法：
    1.使用mybatis中提供的方法（Oracle与MySQL通用）
      select * from t_user 
      where 1 = 1
      <bind name="word" value='"%"+keyword +"%"'/>
      and name like #{word}
    2.如果是MySQL，则使用MySQL自身的方法（concat关键字是 将多个字符串进行拼接）
      select * from t_user where name like concat('%', #{keyWord}, '%')
    3.如果是Oracle,则可以使用Oracle自身方法（|| 将多个字符串进行拼接）
      select * from t_user where name like '%' || #{keyword} || '%' 
      select * from t_user where name like #{keyword} || '%' 

4.
  Oracle数据库，插入数据前自动获取自增主键id的值：
    <selectKey keyProperty="id" order="BEFORE" resultType="java.lang.Long">
    SELECT SEQ_FORUM_POSTS_CATEGORY.nextval AS id from dual
    </selectKey>

    dual ：是oracle 数据库中的虚拟表，并不是真实存在的
    SEQ_FORUM_POSTS_CATEGORY：这个是我们创建序列时自定义的一个序列名称
    SEQ_FORUM_POSTS_CATEGORY.nextval：这个是取出序列的下一个值，序列可以用户id生成器，每次我们都通过序列取到不同的值，并且不会重复。
  注意<selectKey>标签的使用，其中属性有：keyProperty、order、resultType、statementType。
  
5.
  Oracle数据库排序分页查询:
    select * from 
    (select t.*, rownum rn from  PRODUCT_MODEL_BASE t order by OFFERING_NUMBER)
    where rn between 2 and 10 ;
  或者
    select * from 
    (select t.*, rownum rn from  PRODUCT_MODEL_BASE t)
    where rn between 2 and 10 
    order by OFFERING_NUMBER;

6.
  // 读取上传的CSV文件file，封装到对应的实体类集合中
	坐标：    <!-- csv文件解析依赖 -->
		        <dependency>
		            <groupId>com.opencsv</groupId>
		            <artifactId>opencsv</artifactId>
		            <version>4.3.2</version>
		        </dependency>

  private List<ProjectInfoDO> getCsvData(MultipartFile file) {
      List<ProjectInfoDO> projectInfoDOList = new ArrayList<>();
      try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream(),"GBK"), ',')) {
          List<String[]> records = reader.readAll();
          Iterator<String[]> iterator = records.iterator();
          //skip header row
          iterator.next();
          while(iterator.hasNext()){
              String[] record = iterator.next();
              ProjectInfoDO projectInfoDO = new ProjectInfoDO();
              projectInfoDO.setProjectNumber(record[0]);
              projectInfoDO.setStatus(ProjectStatus.getCodeByName(record[1]));
              projectInfoDO.setId(Long.valueOf(record[2]));
              projectInfoDO.setComment(record[3]);
              projectInfoDOList.add(projectInfoDO);
          }
      } catch (IOException e) {
          logger.error("解析csv文件失败：", e);
          return null;
      }
      return projectInfoDOList;
  }

7.
	@requestparam( required = false) 的作用？
    不传值后台也不会报错，但是如果@requestparam( required = false)的括号中指定了基本数据类型，
		例如(@requestparam(value = 'num' required = false)  int num) 这个时候如果不传值是会报错的，
		因为不传值就赋null,但是int类型不能为null,解决办法，修改成Integer即可。
