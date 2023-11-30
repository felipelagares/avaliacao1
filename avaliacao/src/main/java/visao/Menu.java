package visao;

import negocio.Curso;
import negocio.Disciplina;

import biblioteca.CursoDAO;
import biblioteca.DisciplinaDAO;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

public class Menu {

    public static ArrayList<Object> getAllItems (NodeList cursoList){

        List<Curso> cursos = new ArrayList<>();
        List<Disciplina> disciplinas = new ArrayList<>();

        for(int i = 0; i<cursoList.getLength(); i++){
            Node cNode = cursoList.item(i);//representa um nó do tipo curso
            if(cNode.getNodeType()==Node.ELEMENT_NODE){
                Element cElement = (Element) cNode;//representa um elemento tipo curso
                NodeList cAtributes = cElement.getChildNodes();//nós filhos do curso

                int iden = 0;
                String cursoNome = "";
                int ano = 0;
                String disciplinaNome = "";
                int ch = 0;

                for(int j=0; j<cAtributes.getLength();j++){
                    Node atribute = cAtributes.item(j);
                    if(atribute.getNodeType()==Node.ELEMENT_NODE){
                        String nodeName = atribute.getNodeName();
                        switch (nodeName){
                            case "iden": iden = Integer.parseInt(atribute.getTextContent());
                                break;
                            case "ano": ano = Integer.parseInt(atribute.getTextContent());
                                break;
                            case "nome": cursoNome = atribute.getTextContent();
                                break;
                            case "disciplina": disciplinaNome = atribute.getTextContent();
                                break;
                            case "ch": ch = Integer.parseInt(atribute.getTextContent());
                                break;
                        }
                    }
                }

                Curso c = new Curso(iden, ano, cursoNome);
                Disciplina d = new Disciplina(iden, ch, disciplinaNome);
                cursos.add(c);
                disciplinas.add(d);
            }
        }
        ArrayList<Object> retorno = new ArrayList<>();
        retorno.add(cursos);
        retorno.add(disciplinas);
        return retorno;
    }

    public static int showOptions(){
        System.out.println("1. Pesquisar por texto"+"\n"+
                "2. Pesquisar por ano e texto"+"\n"+
                "3. Listar todos os cursos"+"\n"+
                "4. Exportar para um banco de dados"+"\n"+
                "5. Sair"+"\n"+
                "Escolha: ");
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public static void main(String[] args) {
        String caminhoDadosXml = "C://Users//felip//Desktop//avaliacaopdd//avaliacao1//avaliacao//dados.xml";

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(caminhoDadosXml);

            NodeList cursoList = doc.getElementsByTagName("curso");

            int choice = showOptions();
            Scanner sc = new Scanner(System.in);

            ArrayList items = getAllItems(cursoList);
            List<Curso> cList = (List<Curso>) items.get(0);//lista de cursos
            List<Disciplina> dList = (List<Disciplina>) items.get(1);//lista de disciplinas

            while(choice != 5){

                ArrayList result = new ArrayList<>();

                switch (choice){
                    case 1:
                        System.out.println("texto para buscar: ");
                        String textoBuscado = sc.nextLine();
                        for (Curso curso : cList) {
                            if (curso.getNome().contains(textoBuscado)) {
                                result.add(curso);
                            }
                        }
                        for (Disciplina disciplina : dList) {
                            if (disciplina.getNome().contains(textoBuscado)) {
                                result.add(disciplina);
                            }
                        }
                        for(int i=0; i< result.size(); i++){
                            System.out.println(result.get(i));
                        }
                        break;
                    case 2:
                        System.out.println("texto para buscar: ");
                        textoBuscado = sc.nextLine();
                        System.out.println("ano para buscar: ");
                        int anoBuscado = sc.nextInt();
                        for (Curso curso : cList) {
                            if (curso.getAno() == anoBuscado){
                                if (curso.getNome().contains(textoBuscado)) {
                                    result.add(curso);
                                }
                            }
                        }
                        for(int i=0; i< result.size(); i++){
                            System.out.println(result.get(i));
                        }
                        break;
                    case 3:
                        result.addAll(cList);
                        for(int i=0; i< result.size(); i++){
                            System.out.println(result.get(i));
                        }
                        break;
                    case 4:
                        CursoDAO cdao = new CursoDAO();
                        for(Curso curso : cList){
                            cdao.incluir(curso);
                        }
                        DisciplinaDAO ddao = new DisciplinaDAO();
                        for(Disciplina disc : dList){
                            ddao.incluir(disc);
                        }
                        break;
                }

                choice = showOptions();
            }

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }


    }
}
