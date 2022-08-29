package com.yuye.Prototype.DeepClone;

import java.io.*;

/**
 * author ye
 * createDate 2022/1/27  10:20
 */
public class DeepPrototype implements Serializable,Cloneable {
    private static final long serialVersionUID = 5549436988591593340L;
    public String name;
    public DeepCloneableTest deepCloneableTest;//应用类型
    public DeepPrototype(){
        super();
    }

    //深拷贝-使用clone方式
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object deepClone = null;
        //对基本数据类型和String的拷贝
        deepClone = super.clone();
        //对应用进行单独处理
        DeepPrototype deepPrototype = (DeepPrototype) deepClone;
        deepPrototype.deepCloneableTest = (DeepCloneableTest) deepCloneableTest.clone();
        return deepPrototype;
    }
    //通过对象的序列化实现
    public Object deepClone(){
        ByteArrayOutputStream bo = null;
        ByteArrayInputStream bi = null;
        ObjectOutputStream oo = null;
        ObjectInputStream oi = null;
        try {
            //序列化
            bo = new ByteArrayOutputStream();
            oo = new ObjectOutputStream(bo);
            oo.writeObject(this);

            //反序列化
            bi = new ByteArrayInputStream(bo.toByteArray());
            oi = new ObjectInputStream(bi);
            DeepPrototype deepPrototype = (DeepPrototype) oi.readObject();
            return deepPrototype;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            try {
                if (bo != null){
                    bo.close();
                }
                if (bi != null){
                    bi.close();
                }
                if (oo != null){
                    oo.close();
                }
                if (oi != null){
                    oi.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
