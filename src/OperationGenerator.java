import java.util.Random;

public class OperationGenerator {


    public OperationGenerator(int operationCount) {
        //this.operationCount = operationCount;
    }

    public Operation getOperation() {
        Random random = new Random();
        return Operation.values()[random.nextInt(Operation.values().length)];
    }




}
