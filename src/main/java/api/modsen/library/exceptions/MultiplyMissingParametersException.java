package api.modsen.library.exceptions;

import java.util.List;

import static api.modsen.library.config.LibraryAppConstants.*;

public class MultiplyMissingParametersException extends RuntimeException {
    private final List<String> missingParameters;

    public MultiplyMissingParametersException(List<String> missingParameters) {
        super(MISSING_PARAMETERS_MESSAGE + String.join(", ", missingParameters));
        this.missingParameters = missingParameters;
    }

    public List<String> getMissingParameters() {
        return this.missingParameters;
    }
}
