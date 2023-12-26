package org.arnhold.evaluation.constraints.shacl;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.shacl.ValidationReport;

public interface ShaclValidationService {
    ValidationReport validateShape(Model maDMPGraph, Model shapesGraph);
}
