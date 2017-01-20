package Helper.Saver.XMLFile;

import Helper.FileExceptions.MyFileException;
import Helper.Saver.AbstractSaver;
import com.sun.org.apache.xml.internal.serializer.OutputPropertiesFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.List;

/**
 * Created by andrei on 2017-01-20.
 */
public abstract class XMLFileSaver<T> extends AbstractSaver<T> {
    public XMLFileSaver() {
    }

    @Override
    public void save(List<T> list, String fileName) throws MyFileException {
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document document = docBuilder.newDocument();

            Node root = document.createElement(getRoot());
            document.appendChild(root);
            for(T e : list)
            {
                Element element = createElementFromFormat(document, e);
                root.appendChild(element);
            }

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(fileName);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputPropertiesFactory.S_KEY_INDENT_AMOUNT, "2");
            transformer.transform(source,result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract Element createElementFromFormat(Document document, T element) throws MyFileException;

    public abstract String getRoot();

}
