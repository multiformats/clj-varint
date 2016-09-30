(def project 'multiformats/clj-varint)
(def version "0.2.0-SNAPSHOT")

(set-env! :resource-paths #{"resources" "src/clj"}
          :source-paths   #{"src/java" "test"}
          :dependencies   '[[org.clojure/clojure "RELEASE"]
                            [adzerk/boot-test "RELEASE" :scope "test"]]
          :project        project
          :version        version)

(task-options!
 pom {:project     project
      :version     version
      :description "Wrapper around Bazel varint"
      :url         "http://github.com/mpare-net/clj-varint"
      :scm         {:url "https://github.com/mpare-net/clj-varint"}
      :license     {"Eclipse Public License"
                    "http://www.eclipse.org/legal/epl-v10.html"}})

(deftask build
  "Build and install the project locally."
  []
  (comp (javac) (pom) (jar) (install)))

(require '[adzerk.boot-test :refer [test]])
