package io.github.cchenxi.w7.db.insert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DbInsertDataApplicationTests {

    @Autowired
    private InsertDataService insertDataService;

    @Test
    public void testCase1() {
        insertDataService.insertByJdbcExecute();
    }

    @Test
    public void testCase2() {
        insertDataService.insertByPrepareStatementAddBatch();
    }
}
