# Concourse

Concourse is a tool to make planning gatherings easy, even if you
don't know the schedule of everyone who can attend, or even who can
attend in the first place. It's based on an (application written for
Railsday 2006)[http://technomancy.us/47].

# The Concept

OK, so you've got this meeting, right? A bunch of people have to come
to this meeting, but you don't know when they are going to be
available. So what you do is sign on to Concourse and create a new
meeting. You give provide some details and a list of folks you'd like
to attend. When you're done, each person gets an email asking them to
please enter their availability for the meeting.

Each person can then log in with the URL provided in the email to
specify the times they can make it to the meeting. In theory, when
everyone had entered their times, the organizer would then be asked to
decide upon a time, and his decision would be informed by what
everyone had said.

# Starting

Eval the file concourse.el from within Emacs to get a server running
on port 8888 via SLIME. All dependencies are bundled except the JVM
(1.5+), Emacs, and SLIME.
