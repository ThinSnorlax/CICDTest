package snor.test;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.ListTablesRequest;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;

import java.util.ArrayList;
import java.util.List;

public class AWSTest {

    public static List<String> getDynamoDBTables(){
        final AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.defaultClient();

        ListTablesRequest request;

        boolean more_tables = true;
        String last_name = null;
        List<String> table_names = null;
        while(more_tables) {
            try {
                if (last_name == null) {
                    request = new ListTablesRequest().withLimit(10);
                } else {
                    request = new ListTablesRequest()
                            .withLimit(10)
                            .withExclusiveStartTableName(last_name);
                }

                ListTablesResult table_list = ddb.listTables(request);
                table_names = table_list.getTableNames();

                if (table_names.size() > 0) {
                    for (String cur_name : table_names) {
                        System.out.format("* %s\n", cur_name);
                    }
                } else {
                    System.out.println("No tables found!");
                    System.exit(0);
                }

                last_name = table_list.getLastEvaluatedTableName();
                if (last_name == null) {
                    more_tables = false;
                }
            }catch (Exception e) {
                e.printStackTrace();
                table_names = new ArrayList<>();
                table_names.add(e.toString());
                break;
            }
        }
        if (table_names == null || table_names.size() == 0) {
            table_names = new ArrayList<>();
            table_names.add("No tables found!");
        }
        return table_names;
    }

}
