(ns autoweka-wrapper.core
  (:gen-class)
  (:require [clj-ml.io :as ml-io]
            [clj-ml.data :as ml-data])
  (:import [weka.classifiers.meta AutoWEKAClassifier AutoWEKAClassifier$Metric]))


(defn metric
  "get the metric corresponding to the keyword"
  [metric-str]

      (let [metrics  {:areaAboveROC AutoWEKAClassifier$Metric/areaAboveROC,
                      :areaUnderROC AutoWEKAClassifier$Metric/areaUnderROC,
                      :avgCost AutoWEKAClassifier$Metric/avgCost,
                      :correct AutoWEKAClassifier$Metric/correct,
                      :correlationCoefficient AutoWEKAClassifier$Metric/correlationCoefficient,
                      :errorRate AutoWEKAClassifier$Metric/errorRate,
                      :falseNegativeRate AutoWEKAClassifier$Metric/falseNegativeRate,
                      :falsePositiveRate AutoWEKAClassifier$Metric/falsePositiveRate,
                      :fMeasure AutoWEKAClassifier$Metric/fMeasure,
                      :incorrect AutoWEKAClassifier$Metric/incorrect,
                      :kappa AutoWEKAClassifier$Metric/kappa,
                      :kBInformation AutoWEKAClassifier$Metric/kBInformation,
                      :kBMeanInformation AutoWEKAClassifier$Metric/kBMeanInformation,
                      :kBRelativeInformation AutoWEKAClassifier$Metric/kBRelativeInformation,
                      :meanAbsoluteError AutoWEKAClassifier$Metric/meanAbsoluteError,
                      :pctCorrect  AutoWEKAClassifier$Metric/pctCorrect,
                      :pctIncorrect  AutoWEKAClassifier$Metric/pctIncorrect,
                      :precision  AutoWEKAClassifier$Metric/precision,
                      :relativeAbsoluteError AutoWEKAClassifier$Metric/relativeAbsoluteError,
                      :rootMeanSquaredError  AutoWEKAClassifier$Metric/rootMeanSquaredError,
                      :rootRelativeSquaredError  AutoWEKAClassifier$Metric/rootRelativeSquaredError,
                      :weightedAreaUnderROC AutoWEKAClassifier$Metric/weightedAreaUnderROC,
                      :weightedFalseNegativeRate  AutoWEKAClassifier$Metric/weightedFalseNegativeRate,
                      :weightedFalsePositiveRate  AutoWEKAClassifier$Metric/weightedFalsePositiveRate,
                      :weightedFMeasure  AutoWEKAClassifier$Metric/weightedFMeasure,
                      :weightedPrecision  AutoWEKAClassifier$Metric/weightedPrecision,
                      :weightedRecall AutoWEKAClassifier$Metric/weightedRecall,
                      :weightedTrueNegativeRate AutoWEKAClassifier$Metric/weightedTrueNegativeRate,
                      :weightedTruePositiveRate  AutoWEKAClassifier$Metric/weightedTruePositiveRate}]
        (get metrics metric-str)))


(defn training-data
  "load training data.
  accepts: path to training data, class-name"
  [path class-name]

  (-> (ml-io/load-instances :csv path)
      (ml-data/dataset-set-class class-name)))


(defn autoweka
  "run autoweka classifiers on the dataset
  accepts: dataset
           optional-params: memory-limit,metric,parallel-runs,n-best-configs,seed,time-limit

  "
  [dataset & {:keys [memory-limit
                     metric-str
                     parallel-runs
                     n-best-configs
                     seed
                     time-limit]

              :or {memory-limit 1024
                   metric-str :areaUnderROC
                   parallel-runs 1
                   n-best-configs 1
                   seed 1
                   time-limit 15}}]

  (let [aw (doto
              (AutoWEKAClassifier.)
              (.setMemLimit memory-limit)
              (.setMetric (metric metric-str))
              (.setParallelRuns parallel-runs)
              (.setnBestConfigs n-best-configs)
              (.setSeed seed)
              (.setTimeLimit time-limit)
              (.buildClassifier dataset))]

    (.toString aw)))


(defn -main
  "perform a sample run of autoweka"
  [& args]

  (let [dataset (training-data "resources/train.csv")

        _ (prn "started training")

        auto-weka-res (autoweka dataset
                                :memory-limit 2048
                                :parallel-runs 2
                                :n-best-configs 5
                                :seed 2016
                                :metric-str :areaUnderROC
                                :time-limit 600)]
    (prn auto-weka-res)
    (spit "autoweka-result.txt" auto-weka-res)))

