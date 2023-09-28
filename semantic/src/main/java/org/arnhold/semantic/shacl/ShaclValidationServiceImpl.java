package org.arnhold.semantic.shacl;

import org.apache.jena.graph.Graph;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.shacl.ShaclValidator;
import org.apache.jena.shacl.Shapes;
import org.apache.jena.shacl.ValidationReport;
import org.springframework.stereotype.Service;

@Service
public class ShaclValidationServiceImpl implements ShaclValidationService {

    @Override
    public ValidationReport validateShape(Model maDMPGraph, Model shapesModel) {

        Shapes shapes = Shapes.parse(shapesModel);
        Graph graph = shapesModel.getGraph();

        return ShaclValidator.get().validate(shapes,  graph);
    }
}
