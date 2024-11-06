public class GradesExceedGradesCountException extends  RuntimeException {

/***
 *  A  custom exception I wrote to handle the unchecked exception that could
 *  occur when the method is invoked
 */
    public GradesExceedGradesCountException(String message) {
        super(message);
    }
}
