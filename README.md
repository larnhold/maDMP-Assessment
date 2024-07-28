# maDMP-Assesment Framework
This repository contains the sources codes and other outputs from the Master Thesis  *Automated Indicators on the Quality of Machine-actionable Data Management Plans*.

## Table of Contents
- [DMP Quality Vocabulary](#dmp-quality-vocabulary)
- [Conceptual Framwork](#conceptual-framework)
- [Prototype Implementation](#prototype-implementation)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## DMP Quality Vocabulary
The primary output of the evaluation of a maDMP in the scope of the solution are quality measurements representing quality measurements as described by the DMP Quality Vocabulary. This vocabulary also includes other information relevant to the representation of the result of a DMP Evaluation. The [DMPQV Documentation](https://larnhold.github.io/maDMP-Assesment/dmpqv/index.html) contains more information regarding this vocabulary.

## Conceptual Framework
The conceptual framework specifies components that enable the interaction if DMPs to generate automated quality indicators to assist in the evaluation of DMPs. The main components are shown in the pictures below.
![Overview of Components.](/assets/images/component-overview.png)

* **DMP Loader**: Loads DMPs from different sources into a normalized form.
* **DMP Harvester Service**: Abstracts access to all required information for the evaluation of DMPs. This includes provision of the DMP itself and all available resolved contextual information.
* **Data Store**: Provides unified storage access for artifacts being used in the framework.
* **DMP Indicator Service**: Coordinates the process of generating quality indicators for DMPs and provides services to retrieve the artifacts resulting from a DMP evaluation as well aggregations of measurement results.
* **Context Loader**: Resolves information referenced in a DMP from external sources.
* **Evaluator**: Provides quality measurements for a certain quality dimension.

## Prototype Implementation
In this project we implemented the proposed conceptual framework for the automated evaluation of DMS. The figure below shows the deployment diagram as is implemented in this repository. For more details on the implemented instances please consult the associated thesis.

![Deployment Diagram.](/assets/images/deployment-diagram.png)

Although the implemented prototype runs in the context of one single spring application,
we split the dependencies between different packages of the implementation into three
modules: _core_, _case-study_ and _sdk_. The _core_ package contains
the implementations of the DMP Harvester Service as well as the DMP Indicator
Service which are parts of the proposed framework whose implementation is independent
of the specific evaluation use case. 

The _case-study_ package contains those use case
dependent components such as instances of DMP Loader, Context Loader, Data Store
and Evaluator. 

To provide a connection between components in these two packages, the _sdk_ package provides necessary definitions of the interfaces and data structures to
facilitate communication between components of the core and case-study packages.

![Package Diagram.](/assets/images/package-diagram.png)

## Installation
We use Maven as a build system and therefore it is required before installation of the project. Afterwards following commands download and setup the project accordingly.

1. Clone the repository:
```bash
 git clone https://github.com/larnhold/maDMP-Assesment.git
```

2. Install dependencies:
```bash
mvn clean compile install
```

3. Generate sources and update folders:
```bash
mvn generate-sources 
```

## Usage
To make use of all functions provided in the reference implementation some additional external services are required, in particular the reference implementation accesses a locally hosted instance of the F-UJI FAIR evaluator. For convenience the project includes a [Docker Compose File](docker/docker-compose.yml) to host this service. 
Therefore these services can be run using the following command:
```bash
docker-compose -f docker/docker-compose.yml up
```

To run the prototype implementation of the DMP evaluation framework, use the following command:
```bash
mvn -f core spring-boot:run
```

The project includes a Swagger documentation which is hosted on a local [Swagger](http://localhost:8080/swagger-ui/index.html) instance when running the application.

In addition to that the project also includes a [Jupyter Notbeook](scripts/evaluation.ipynb) which interactively shows how to use the services provided by the evaluation framework as described in the associated thesis.

## License
This project is licensed under the [MIT License](LICENSE).