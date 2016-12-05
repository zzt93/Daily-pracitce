/**
 * Created by zzt on 12/5/16.
 * <p>
 * <h3>First MapReduce job, try to find the related words in each line</h3>
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FirstJob {
    //Mapper class
    public static class LineToWordMapper extends Mapper
            <Text,        /*Input key Type */
                    Text,                /*Input value Type*/
                    Text,                /*Output key Type*/
                    Text>        /*Output value Type*/ {

        @Override
        protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
            for (String word : value.toString().split("\\s")) {
                context.write(key, new Text(value + ":" + word));
            }
        }
    }


    //Reducer class
    public static class WordsReducer extends Reducer<Text, Text, Text, Text> {

        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            Text res = new Text();
            for (Text value : values) {
                res.append(value.getBytes(), 0, value.getLength());
                res.append(new byte[]{'\n'}, 0, 1);
            }
            context.write(key, res);
        }
    }


    //Main function
    public static void main(String args[]) throws Exception {
        Configuration conf = new Configuration();
    }
}