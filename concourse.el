;;; Launch Concourse from SLIME.

;; This doesn't seem to work if you've already launched SLIME from
;; this Emacs instance; even if you have since disconnected. Not sure
;; how to "reset" the connection.

(ignore-errors
  (zenburn) (envy "20"))

(setq concourse-dir (file-name-directory
                     (or (buffer-file-name) load-file-name))
      swank-clojure-jar-path (concat concourse-dir "/jars/clojure.jar")
      swank-clojure-extra-classpaths (directory-files (concat concourse-dir "/jars/") t ".jar$"))

(defun concourse-launch ()
  (interactive)
  (slime-load-file (concat concourse-dir "app/boot.clj")))

(defun concourse-test ()
  (interactive)
  (dolist (test-file '("test/test-models.clj"))
    (slime-load-file (concat concourse-dir test-file)))
  (slime-repl-eval-string "(clojure.contrib.test-is/run-tests 'concourse.test)"))

(add-hook 'slime-connected-hook 'concourse-launch)

(slime)

(find-file (concat concourse-dir "/app/concourse.clj"))