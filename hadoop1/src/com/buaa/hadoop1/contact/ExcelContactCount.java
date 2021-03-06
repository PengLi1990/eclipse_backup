package com.buaa.hadoop1.contact;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelContactCount extends Configured implements Tool {
	private static Logger logger = LoggerFactory.getLogger(ExcelContactCount.class);

	public static class ExcelMapper extends
			Mapper<LongWritable, Text, Text, Text> {

		private static Logger LOG = LoggerFactory.getLogger(ExcelMapper.class);
		private Text pkey = new Text();
		private Text pvalue = new Text();

		/**
		 * Excel Spreadsheet is supplied in string form to the mapper. We are
		 * simply emitting them for viewing on HDFS.
		 */
		public void map(LongWritable key, Text value, Context context)
				throws InterruptedException, IOException {
			//1.0, 閼颁胶鍩� 13999123786, 2014-12-20
			String line = value.toString();
			String[] records = line.split("\\s+");
			String[] months = records[3].split("-");//閼惧嘲褰囬張鍫滃敜
			pkey.set(records[1] + "\t" + months[1]);//閺勭數袨+閺堝牅鍞�
			pvalue.set(records[2]);//閹靛婧�崣锟�			context.write(pkey, pvalue);
			LOG.info("Map processing finished");
		}
	}

	public static class PhoneReducer extends Reducer<Text, Text, Text, Text> {
		private Text pvalue = new Text();

		protected void reduce(Text Key, Iterable<Text> Values, Context context)
				throws IOException, InterruptedException {
			int sum = 0;
			Text outKey = Values.iterator().next();
			for (Text value : Values) {
				sum++;
			}
			pvalue.set(outKey+"\t"+sum);
			context.write(Key, pvalue);
		}
	}

	public static class PhoneOutputFormat extends
			MailMultipleOutputFormat<Text, Text> {

		@Override
		protected String generateFileNameForKeyValue(Text key,
				Text value, Configuration conf) {
            //name+month
            String[] records = key.toString().split("\t"); 
			return records[1] + ".txt";
		}

	}

	@Override
	public int run(String[] args) throws Exception {
		Configuration conf = new Configuration();// 闁板秶鐤嗛弬鍥︽鐎电钖�
		Path mypath = new Path(args[1]);
		FileSystem hdfs = mypath.getFileSystem(conf);// 閸掓稑缂撴潏鎾冲毉鐠侯垰绶�
		if (hdfs.isDirectory(mypath)) {
			hdfs.delete(mypath, true);
		}
		logger.info("Driver started");

		Job job = new Job();
		job.setJarByClass(ExcelContactCount.class);
		job.setJobName("Excel Record Reader");
		job.setMapperClass(ExcelMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		job.setInputFormatClass(ExcelInputFormat.class);//閼奉亜鐣炬稊澶庣翻閸忋儲鐗稿锟�		
		job.setReducerClass(PhoneReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		job.setOutputFormatClass(PhoneOutputFormat.class);//閼奉亜鐣炬稊澶庣翻閸戠儤鐗稿锟�
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		job.waitForCompletion(true);
		return 0;
	}

	public static void main(String[] args) throws Exception {
		String[] args0 = { "hdfs://lp1:9000/contact/phone.xls",
				"hdfs://lp1:9000/contact/phone-out/" };
		int ec = ToolRunner.run(new Configuration(), new ExcelContactCount(), args0);
		System.exit(ec);
	}
}
