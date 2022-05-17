**Scale**

<p>If the number of users increased:</p>

<p>A good solution would be to use a nosql MongoDB or other distributed database cluster.
This would be to make the rating more consistent.
You could then run n instances of the application in the cluster such as Kubernetes or Open Shift
and use the Load Balancer to evenly split the application traffic.</p>