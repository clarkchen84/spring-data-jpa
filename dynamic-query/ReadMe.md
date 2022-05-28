#### Query BY Example
1. 只支持查询
   1. 不支持嵌套和分组的属性约束，如firstName=？0 or (firstName=?1 and lastname = ?2)
   2. 只支持字符串start/contains/ends/regex 匹配和其他属性的精确匹配
2. 继承`QueryByExampleExecutor`