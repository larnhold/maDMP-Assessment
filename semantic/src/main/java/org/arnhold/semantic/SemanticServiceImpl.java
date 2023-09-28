package org.arnhold.semantic;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.riot.RDFDataMgr;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class SemanticServiceImpl implements SemanticService {

    public SemanticServiceImpl() {
    }

    @Override
    public Model loadModelFromFile(File file) {
        return RDFDataMgr.loadModel(file.getAbsolutePath());
    }
}
