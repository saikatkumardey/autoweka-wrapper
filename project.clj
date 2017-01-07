(defproject com.saikatkumardey/autoweka-wrapper "0.1.0-SNAPSHOT"
  :description "A clojure wrapper for autoweka which provides automatic selection of models and hyperparameters"
  :url "https://github.com/saikatkumardey/autoweka-wrapper"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [cc.artifice/clj-ml "0.8.0"]]
  :main ^:skip-aot autoweka-wrapper.core
  :target-path "target/%s"
  :resource-paths ["autoweka.jar"]
  :profiles {:uberjar {:aot :all}})
