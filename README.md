# core-async-intro

This is the source code used for my presentation [Intro to core.async](http://www.slideshare.net/borgesleonardo/intro-to-clojures-coreasync) for the Sydney Clojure User Group, July 2013

## Prerequisites

You will need [Leiningen][1] 2.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Running

First, compile all clojurescript:

    lein cljsbuild once

or 

	lein cljsbuild auto

Then, start the web server:

    lein ring server
    
And navigate to [http://localhost:3000/](http://localhost:3000/)

## Files you'll be interested in

### Routes

`cljsexample.clj`

### Source code for the examples:

`src-cljs/main.cljs` as well as the templates `coreasync-1.html` and `coreasync-2.html`

Example 2 has a pure javascript for comparison purposes at `js-core-async.js` 

## Acknowledgements

Example 1 is a port of Rob Pike's talk [Go Concurrency Patterns](talks.golang.org/2012/concurrency.slide) and Example 2 was taken from David Nolen's post [Communicating Sequential Processes](http://swannodette.github.io/2013/07/12/communicating-sequential-processes/).

## License

Copyright © 2013 Leonardo Borges
