package com.xiezongnan.api.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

/**
 * 账号表 表定义层。
 *
 * @author petrus
 * @since 2023-08-07
 */
public class TbAccountTableDef extends TableDef {

    /**
     * 账号表
     */
    public static final TbAccountTableDef tbAccountEntity = new TbAccountTableDef();

    /**
     * id
     */
    public final QueryColumn id = new QueryColumn(this, "id");

    /**
     * 年龄
     */
    public final QueryColumn age = new QueryColumn(this, "age");

    /**
     * 用户名
     */
    public final QueryColumn account = new QueryColumn(this, "account");

    /**
     * 生日
     */
    public final QueryColumn birthday = new QueryColumn(this, "birthday");

    /**
     * 姓名
     */
    public final QueryColumn userName = new QueryColumn(this, "user_name");

    /**
     * 所有字段。
     */
    public final QueryColumn allColumns = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] defaultColumns = new QueryColumn[]{id, account, userName, age, birthday};

    public TbAccountTableDef() {
        super("petrus_study_plus", "tb_account");
    }

}
