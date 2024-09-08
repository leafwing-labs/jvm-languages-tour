(ns tech.leafwinglabs.min-max)

;; ways to return [min max] over some sequence
(defn min-max
  [xs]
  (reduce
    (fn [[min max] x]
      [(if (or (nil? min) (< x min)) x min)
       (if (or (nil? max) (> x max)) x max)])
    [nil nil]
    xs))

(defn min-max-2
  [xs]
  (reduce (fn [[min max] x]
            [(min min x) (max max x)])
          [Integer/MAX_VALUE Integer/MIN_VALUE]
          xs))

(defn min-max-3
  [xs]
  (->> xs
       (map #(* % %))
       (reduce (fn [[min max] x]
                 [(min min x) (max max x)])
               [Integer/MAX_VALUE Integer/MIN_VALUE])))

(defn min-max-4
  [xs]
  (letfn [(min-max [[p q] x]
            [(min p x) (max q x)])]
    (reduce min-max
            [Integer/MAX_VALUE Integer/MIN_VALUE]
            xs)))

(defn min-max-5
  [xs]
  (loop [xs xs, min Integer/MAX_VALUE, max Integer/MIN_VALUE]
    (if (empty? xs)
      [min max]
      (let [x (first xs)]
        (recur (rest xs)
               (min min x)
               (max max x))))))
b
