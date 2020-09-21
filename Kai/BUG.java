1.
  hikvision-mall  Sonar项目修复3.0  测试环境签章报错。
  原因：使用SecureRandom类生产随机数会导致线程阻塞。
  恢复原状：使用Random类来生产随机数。
 
2.
  spring配置如下：
    pis:
      apiKey: 064000000
  程序中取值：
    @Value("${pis.cloudApi.apiKey}")
    privateStringpisCloudApiKey;
  spring会把064000000当作八进制，输出十进制结果：364110000
    tips：以0开头的数字spring会默认当作八进制转换成十进制输出
  修改方案，加引号：
    pis:
      apiKey: '064000000'

3.
  【对象引用的问题】引用同一个set集合对象，导致一处修改，其它处处也会修改。（可以看每个set的hashCode是否相同）
  错误代码:
  categoryApproveList.forEach(
      c->{
        Set<String> tempApproveNoteSet = Sets.newHashSet();
        if(c.getCategoryIds() != null && c.getApproveNotes() != null){
          String[] categoryIds = c.getCategoryIds().split(",");
          String[] approveNotes = c.getApproveNotes().split(",");
          Collections.addAll(tempApproveNoteSet, approveNotes);
          Arrays.stream(categoryIds).forEach(
              categoryId-> {
                if(categoryApproveMap.get(categoryId) == null){
                  categoryApproveMap.put(categoryId, tempApproveNoteSet);
                }else {
                  Set<String> tempSet = categoryApproveMap.get(categoryId);
                  Collections.addAll(tempSet, approveNotes);
                  categoryApproveMap.put(categoryId, tempSet);
                }
              }
          );
        }
      }
  );
  正确代码：
  categoryApproveList.forEach(
      c->{
        if(c.getCategoryIds() != null && c.getApproveNotes() != null){
          String[] categoryIds = c.getCategoryIds().split(",");
          String[] approveNotes = c.getApproveNotes().split(",");
          Arrays.stream(categoryIds).forEach(
              categoryId-> {
                if(categoryApproveMap.get(categoryId) == null){
                  Set<String> tempApproveNoteSet = Sets.newHashSet();
                  Collections.addAll(tempApproveNoteSet, approveNotes);
                  categoryApproveMap.put(categoryId, tempApproveNoteSet);
                }else {
                  Set<String> tempSet = categoryApproveMap.get(categoryId);
                  Collections.addAll(tempSet, approveNotes);
                  categoryApproveMap.put(categoryId, tempSet);
                }
              }
          );
        }
      }
  );
