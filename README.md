# autoweka-wrapper

A clojure wrapper for autoweka which provides automatic selection of models and hyperparameters


[![Clojars Project](https://img.shields.io/clojars/v/com.saikatkumardey/autoweka-wrapper.svg)](https://clojars.org/com.saikatkumardey/autoweka-wrapper)


## Installation

Just add this line to :dependencies in your project.clj

    [com.saikatkumardey/autoweka-wrapper "0.1.0-SNAPSHOT"]


## Examples


    (use autoweka-wrapper.core)

    (def dataset ((training-data path-to-dataset)))

    (def autoweka-result (autoweka dataset
                                :memory-limit 2048
                                :parallel-runs 2
                                :n-best-configs 5
                                :seed 2016
                                :metric-str :areaUnderROC
                                :time-limit 600))
    (prn autoweka-result)
