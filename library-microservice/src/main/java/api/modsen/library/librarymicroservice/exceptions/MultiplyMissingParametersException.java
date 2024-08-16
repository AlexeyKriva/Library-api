package api.modsen.library.librarymicroservice.exceptions;

import api.modsen.library.librarymicroservice.config.LibraryAppConstants;

import java.util.List;

import static api.modsen.library.librarymicroservice.config.LibraryAppConstants.MISSING_PARAMETERS_MESSAGE;

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
