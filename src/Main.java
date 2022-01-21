import com.fasterxml.jackson.databind.ObjectMapper;
import input.Input;
import simulator.Processing;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * The type Main.
 */
public class Main {
    /**
     * Reads the whole input json as a single instance of the Input class
     *
     * @param name    the file name
     * @param newTest the Current test data to be filled
     */
    public static void readInput(final String name, final Processing newTest) {
        Input input = new Input();
        ObjectMapper mapper = new ObjectMapper();
        try {
            input = mapper.readValue(Paths.get(name).toFile(), Input.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        newTest.fillData(input);
    }

    /**
     * Writes the whole output json as a single instance of the Output class
     *
     * @param name    the file name
     * @param newTest the Current test filled data
     */
    public static void writeOutput(final String name, final Processing newTest) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(Paths.get(name).toFile(),
                    newTest.prepareData());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main.
     *
     * @param args the args
     * @throws Exception the exception
     */
    public static void main(final String[] args) throws Exception {
        Processing newTest = new Processing();
        readInput(args[0], newTest);
        writeOutput(args[1], newTest);
    }
}
