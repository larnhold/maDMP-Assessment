package org.arnhold.semantic;

import org.apache.jena.rdf.model.Model;

import java.io.File;

public interface SemanticService {
    Model loadModelFromFile(File file);
}
