package com.sm.storageregistration;

import java.io.File;
import java.io.FileNotFoundException;

import com.sm.storageregistration.Implements.StorageAwsImpl;

public class Test {

	public static void main(String[] args) throws FileNotFoundException {

		File file = new File("D:\\Softwares\\httpd.conf");
		long long1 = 0x800000L;
		new StorageAwsImpl().sendFile(file.toString(), "in", long1);
	}

}
