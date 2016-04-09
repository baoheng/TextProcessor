"# TextProcessor" 

# Header converts to <h1></h1>
## Header converts to <h2></h2>
### Header converts to <h3></h3>
#### Header converts to <h4></h4>
##### Header converts to <h5></h5>
###### Header converts to <h6></h6>

Note: more than 6 '#' converts to <h6></h6>

Paragraph with tag <p> </p>

This is a one line paragraph. 

This is a longer paragraph that contains 
more than one line. The paragraph *continues*
until we find an empty line. 

Blockquote with <blockquote></blockquote>

> What are the resources allocated? 
> > Are you asking in terms of headcount or machines? 
> > > Headcount


Numbered list 

1. one 
1. two 
  1. inner one 
  1. inner two 
    1. inner inner one
    1. inner inner two
  1. inner three
1. three 

Bullet list 

* one 
* two 
  * inner one 
  * inner two 
    * inner inner one
    * inner inner two
  * inner three
* three 


Mixing lists 

1. one 
1. two 
  * inner one 
  * inner two 
    1. inner inner one
    1. inner inner two
  * inner three
1. three 

1. one 
1. two 
*  inner one 
*  inner two 
1. inner inner one
1. inner inner two
*  inner three
1. three 

Changes to:
<ol>
<li>one </li>
<li>two 

<ol>
<li>inner one </li>
<li>inner two 

<ol>
<li>inner inner one</li>
<li>inner inner two</li>
</ol>
</li>
<li>inner three</li>
</ol>
</li>
<li>three </li>
</ol>

<p>Bullet list </p>

<ul>
<li>one </li>
<li>two 

<ul>
<li>inner one </li>
<li>inner two 

<ul>
<li>inner inner one</li>
<li>inner inner two</li>
</ul>
</li>
<li>inner three</li>
</ul>
</li>
<li>three </li>
</ul>

<p>Mixing lists </p>

<ol>
<li>one </li>
<li>two 

<ul>
<li>inner one </li>
<li>inner two 

<ol>
<li>inner inner one</li>
<li>inner inner two</li>
</ol>
</li>
<li>inner three</li>
</ul>
</li>
<li>three </li>
</ol>

<p>And another one </p>

<ol>
<li>one </li>
<li>two </li>
</ol>
<ul>
<li> inner one </li>
<li> inner two </li>
</ul>
<ol>
<li>inner inner one</li>
<li>inner inner two</li>
</ol>
<ul>
<li> inner three</li>
</ul>
<ol>
<li>three<br>
</li>
</ol>

More than 4 **** converts to <hr>


Emphasis

So here is some *bold* text and some _italicized_ text.

We can also have *_this_*, as well as well as _*this*_.

<p>So here is some <strong>bold</strong> text and some <em>italicized</em> text. </p>

<p>We can also have <strong><em>this</em></strong>, as well as <em><strong>this</strong></em></p>


Hyperlink

You can see an example [Confused](https://media.giphy.com/media/wi9yHmX7Sztuw/giphy.gif). 
You can read more [here](https://en.wikipedia.org/wiki/Confusion)

 <p>You can see an example <a href="https://media.giphy.com/media/wi9yHmX7Sztuw/giphy.gif">Confused</a>. 
You can read more <a href="https://en.wikipedia.org/wiki/Confusion">here</a></p>
