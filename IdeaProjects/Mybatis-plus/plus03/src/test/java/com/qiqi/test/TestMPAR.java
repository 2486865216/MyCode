package com.qiqi.test;


import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import org.junit.Test;


public class TestMPAR {
    @Test
    public void tetsGenerator(){
        //全局配置
        /**
         * fileOverride	        覆盖已生成文件	     默认值:false
         * disableOpenDir	    禁止打开输出目录	 默认值:true
         * outputDir(String)	指定输出目录	     /opt/baomidou/ 默认值: windows:D:// linux or mac : /tmp
         * author(String)	    作者名	         baomidou 默认值:作者
         * enableKotlin	        开启 kotlin 模式	 默认值:false
         * enableSwagger	    开启 swagger 模式	 默认值:false
         * dateType(DateType)	时间策略	         DateType.ONLY_DATE 默认值: DateType.TIME_PACK
         * commentDate(String)	注释日期	         默认值: yyyy-MM-dd
         */
        GlobalConfig config = new GlobalConfig.Builder()
                .author("zhangyuye")
                .outputDir("src\\main\\java")
                .fileOverride()
                .build();
        //配置数据源
        /**
         * dbQuery(IDbQuery)	                数据库查询	                new MySqlQuery()
         * schema(String)	                    数据库 schema(部分数据库适用)	mybatis-plus
         * typeConvert(ITypeConvert)	        数据库类型转换器	            new MySqlTypeConvert()
         * keyWordsHandler(IKeyWordsHandler)    数据库关键字处理器	            new MySqlKeyWordsHandler()
         */
        DataSourceConfig root = new DataSourceConfig.Builder("jdbc:mysql://localhost/mybatisplus"
                , "root", "123456").build();

        //策略配置
        /**
         * enableCapitalMode	        开启大写命名	默认值:false
         * enableSkipView	            开启跳过视图	默认值:false
         * disableSqlFilter	            禁用 sql 过滤	默认值:true，语法不能支持使用 sql 过滤表的话，
         *                                               可以考虑关闭此开关
         * enableSchema	                启用 schema	默认值:false，多 schema 场景的时候打开
         * likeTable(LikeTable)	        模糊表匹配(sql 过滤)	likeTable 与 notLikeTable 只能配置一项
         * notLikeTable(LikeTable)	    模糊表排除(sql 过滤)	likeTable 与 notLikeTable 只能配置一项
         * addInclude(String...)	    增加表匹配(内存过滤)	include 与 exclude 只能配置一项
         * addExclude(String...)	    增加表排除匹配(内存过滤)	include 与 exclude 只能配置一项
         * addTablePrefix(String...)	增加过滤表前缀
         * addTableSuffix(String...)	增加过滤表后缀
         * addFieldPrefix(String...)	增加过滤字段前缀
         * addFieldSuffix(String...)	增加过滤字段后缀
         * entityBuilder	            实体策略配置
         * controllerBuilder	        controller 策略配置
         * mapperBuilder	            mapper 策略配置
         * serviceBuilder	            service 策略配置
         */
        StrategyConfig strategyConfig = new StrategyConfig.Builder()
                .build();

        //包配置
        /**
         * parent(String)	    父包名	            默认值:com.baomidou
         * moduleName(String)	父包模块名	        默认值:无
         * entity(String)	    Entity 包名	        默认值:entity
         * service(String)	    Service 包名	        默认值:service
         * serviceImpl(String)	Service Impl 包名	默认值:service.impl
         * mapper(String)	    Mapper 包名	        默认值:mapper
         * mapperXml(String)	Mapper XML 包名	    默认值:mapper.xml
         * controller(String)	Controller 包名	    默认值:controller
         * other(String)	    自定义文件包名	输出自定义文件时所用到的包名
         * pathInfo(Map<OutputFile, String>)	路径配置信息
         *                                Collections.singletonMap(OutputFile.mapperXml, "D://")
         */
        PackageConfig packageConfig = new PackageConfig.Builder()
                .parent("com.qiqi.mybatisplus")
                .mapper("mapper")
                .entity("beans")
                .controller("Controller")
                .build();

        //整合配置
        AutoGenerator autoGenerator = new AutoGenerator(root);
        autoGenerator.strategy(strategyConfig);
        autoGenerator.global(config);
        autoGenerator.packageInfo(packageConfig);
        autoGenerator.execute();
    }
}
