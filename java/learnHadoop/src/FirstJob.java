/**
 * Created by zzt on 12/5/16.
 * <p>
 * <h3>First MapReduce job, try to find the related words in each line</h3>
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class FirstJob {
    //Mapper class
    public static class LineToWordMapper extends Mapper
            <LongWritable,        /*Input key Type */
                    Text,                /*Input value Type*/
                    Text,                /*Output key Type*/
                    Text>        /*Output value Type*/ {

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            System.out.printf("key: " + key + " value " + value);
            final String[] split = value.toString().split("\\s");
            final Text key1 = new Text(split[0]);
            for (int i = 1; i < split.length; i++) {
                String word = split[i];
                if (word.equals(split[0])) {
                    context.write(key1, new Text(value));
                    break;
                }
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
        Job job = new Job(conf, "key word search");
        job.setJarByClass(FirstJob.class);
        job.setMapperClass(LineToWordMapper.class);
        job.setCombinerClass(WordsReducer.class);
        job.setReducerClass(WordsReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}