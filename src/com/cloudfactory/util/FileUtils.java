package com.cloudfactory.util;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileUtils<E> {

	/**
	 * 从文件读取对象
	 * 
	 * @param filename 要读取的文件名
	 * @return 返回读出来的对象
	 */
	@SuppressWarnings("unchecked")
	public E getData(String filename) {
		String path = "./data/" + filename;
		File f = new File(path);
		if (f.exists()) {
			FileInputStream fis = null;
			ObjectInputStream ois = null;
			try {
				fis = new FileInputStream(f);
				ois = new ObjectInputStream(fis);
				E data = (E) ois.readObject();
				fis.close();
				return data;
			} catch (EOFException e) {

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return null;		
	}

	/**
	 * 将对象写入文件
	 * 
	 * @param data     要写入的对象
	 * @param filename 要写入的文件名
	 */
	public void writeData(E data, String filename) {
		String path = "./data/" + filename;

		File f = new File(path);

		ObjectOutputStream oos = null;
		try {
			FileOutputStream fis = new FileOutputStream(f);
			oos = new ObjectOutputStream(fis);
			oos.writeObject(data);
			oos.flush();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
