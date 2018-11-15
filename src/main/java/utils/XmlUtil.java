package utils;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.Iterator;
import java.util.List;

public class XmlUtil {

	private static Logger logger = LoggerFactory.getLogger(XmlUtil.class);
	/**
	 * 短信模板位置
	 */
	public static String MESSAGE = System.getProperty("user.home")
			+ File.separator + "props" + File.separator + "message.xml";

	/**
	 * 根据路径读取指定节点内容
	 * 
	 * @param path
	 *            文件路径
	 * @param nodeName
	 *            节点名
	 * @param key
	 *            节点的属性key
	 * @return
	 */
	public static String reader(String path, String nodeName, String key) {
		try {
			InputStreamReader reader = new InputStreamReader(
					new FileInputStream(path));
			return XmlUtil.reader(reader, nodeName, key);
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 读取指定属性下面的key
	 * 
	 * @param inputStream
	 *            xml输入流
	 * @param key
	 *            指定读取的key
	 * @return 读取内容
	 * @throws DocumentException
	 *             抛出异常
	 */
	public static String reader(Reader inputStream, String key)
			throws DocumentException {
		SAXReader reader = new SAXReader();
		// 设置不检查DTD
		reader.setEntityResolver(new EntityResolver() {
			@Override
			public InputSource resolveEntity(String publicId, String systemId)
					throws SAXException, IOException {
				return new InputSource(new ByteArrayInputStream(
						"<?xml version='1.0' encoding='UTF-8'?>".getBytes()));
			}
		});
		Document document = reader.read(inputStream);
		Element node = document.getRootElement();
		return listNodes(node, key);
	}

	/**
	 * 读取指定属性下面的key
	 * 
	 * @param inputStream
	 *            xml输入流
	 * @param key
	 *            指定读取的key
	 * @param attrKey
	 *            key父节点的属性attrKey
	 * @return 读取内容
	 * @throws DocumentException
	 *             抛出异常
	 */
	public static String reader(Reader inputStream, String key, String attrKey)
			throws DocumentException {
		SAXReader reader = new SAXReader();
		// 设置不检查DTD
		reader.setEntityResolver(new EntityResolver() {
			@Override
			public InputSource resolveEntity(String publicId, String systemId)
					throws SAXException, IOException {
				return new InputSource(new ByteArrayInputStream(
						"<?xml version='1.0' encoding='UTF-8'?>".getBytes()));
			}
		});
		Document document = reader.read(inputStream);
		Element node = document.getRootElement();
		return listNodes(node, key, attrKey);
	}

	/**
	 * 遍历当前节点元素下面的所有(元素的)子节点
	 *
	 * @param node
	 */
	@SuppressWarnings("unchecked")
	public static String listNodes(Element node, String key) {
		logger.debug("当前节点的名称：：" + node.getName());
		if (key.equals(node.getName())) {
			return node.getTextTrim();
		}
		Iterator<Element> it = node.elementIterator();
		while (it.hasNext()) {
			Element e = it.next();
			String result = listNodes(e, key);
			if (StringUtils.isNotEmpty(result)) {
				return result;
			}
		}
		return "";
	}

	/**
	 * 遍历当前节点元素下面的所有(元素的)子节点
	 * 
	 * @param node
	 */
	@SuppressWarnings("unchecked")
	public static String listNodes(Element node, String key, String attribute) {
		logger.debug("当前节点的名称：：" + node.getName());
		List<Attribute> attributes = node.attributes();
		for (Attribute tempAttr : attributes) {
			if (tempAttr.getValue().equals(attribute)) {
				String result = listNodes(node, key);
				if (StringUtils.isNotEmpty(result)) {
					return result;
				}
			}
		}
		Iterator<Element> it = node.elementIterator();
		while (it.hasNext()) {
			Element e = it.next();
			String result = listNodes(e, key, attribute);
			if (StringUtils.isNotEmpty(result)) {
				return result;
			}
		}
		return "";
	}

	/**
	 * 把document对象写入新的文件
	 *
	 * @param document
	 * @throws Exception
	 */
	public static void writer(Document document,
			OutputStreamWriter outputStream, String encoding) throws Exception {
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding(encoding);
		XMLWriter writer = new XMLWriter(outputStream, format);
		writer.write(document);
		writer.flush();
		writer.close();
	}

	/**
	 * XML转对象
	 * 
	 * @param t
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T xmlToBean(String xmlStr, Class<T> t) {
		try {
			JAXBContext context = JAXBContext.newInstance(t);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (T) unmarshaller.unmarshal(new StringReader(xmlStr));
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * XML转对象
	 * 
	 * @param t
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T xmlToBean(InputStream input, Class<T> t) {
		try {
			JAXBContext context = JAXBContext.newInstance(t);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			BufferedReader br = new BufferedReader(new InputStreamReader(input,
					"UTF-8"));
			StringBuffer buffer = new StringBuffer();
			String line = null;
			while ((line = br.readLine()) != null) {
				buffer.append(line);
			}
			logger.info("xmlToBean==>" + buffer.toString());
			T ts = (T) unmarshaller.unmarshal(new StringReader(buffer
					.toString()));

			return ts;
		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 对象转XML
	 *
	 * @param out
	 * @param to
	 */
	public static String beanToXml(ByteArrayOutputStream out, Object to) {
		try {
			JAXBContext context = JAXBContext.newInstance(to.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(to, out);
			return new String(out.toByteArray(), "UTF-8");
		} catch (JAXBException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
