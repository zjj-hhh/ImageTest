package deal.dao;

import deal.entity.Page;
import deal.entity.usershow;

import java.sql.SQLException;
import java.util.List;

public interface Iuser {
    public List<usershow> queryUsershowByPage(Page page) throws SQLException;
}
