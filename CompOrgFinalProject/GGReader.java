   import java.io.BufferedReader;
    import java.io.FileReader;
    import java.util.ArrayList;
    
    public class GGReader {

        public GGReader(){}
        public ArrayList readFile(String fileName) {
            ArrayList aaa = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    aaa.add(line.trim());
                }
                return aaa;
            } catch (Exception ee) {
                System.err.println(ee);
                return aaa;
            }
        }

    public String readAsString(String fileName) {
            String fil = "";
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    fil=fil+line;
                }
                return fil;
            } catch (Exception ee) {
                System.err.println(ee);
                return fil;
            }
        }

    } 
